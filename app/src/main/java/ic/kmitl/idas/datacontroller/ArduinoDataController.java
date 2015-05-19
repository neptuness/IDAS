package ic.kmitl.idas.datacontroller;

import android.app.Activity;
import android.content.Context;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.util.Log;

import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.util.SerialInputOutputManager;
import com.hoho.android.usbserial.util.SerialInputOutputManagerWithQueue;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//Concrete class to help other to use communication easier
public class ArduinoDataController extends AbstractDataController {

//    Used in Log for application debugging
    private static final String TAG = ArduinoDataController.class.getSimpleName();

//    The port to connect
    private UsbSerialPort usbSerialPort = null;
//    Parameter for connection
    private UsbSerialPortParameters usbSerialPortParameters = null;
//    Communication manager
    private SerialInputOutputManager serialInputOutputManager = null;

//    Thread service class
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

//    Data listener
    private final SerialInputOutputManager.Listener serialInputOutputManagerListener =
            new SerialInputOutputManager.Listener() {

                @Override
                public void onRunError(Exception e) {
                    Log.d(TAG, "Runner stopped.");
//                    Toast.makeText(ArduinoDataController.this.activity, "Error", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNewData(final byte[] data) {

//                    ArduinoDataController.this.activity.runOnUiThread(new Runnable() {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            ArduinoDataController.this.onDataReceive(data);
//                            if (dataReceiver != null)
//                                dataReceiver.onDataReceive(data);
                            if (hasDataReceiver())
                                getDataReceiver().onDataReceive(data);
                        }

                    });
                }
            };



    public ArduinoDataController(Activity activity) {
//        this.activity = activity;
        super(activity);
        usbSerialPortParameters = new UsbSerialPortParameters();
    }

    public ArduinoDataController(Activity activity, UsbSerialPortParameters usbSerialPortParameters) {
//        this.activity = activity;
        super(activity);
        this.usbSerialPortParameters = usbSerialPortParameters;
    }

//    Establish connection
    @Override
    public void connect(){
//        UsbManager usbManager = (UsbManager) activity.getSystemService(Context.USB_SERVICE);
        UsbManager usbManager = (UsbManager) getActivity().getSystemService(Context.USB_SERVICE);
        UsbDeviceConnection usbDeviceConnection = usbManager.openDevice(usbSerialPort.getDriver().getDevice());

        try{
            usbSerialPort.open(usbDeviceConnection);

            usbSerialPort.setParameters(usbSerialPortParameters.getBaudRate(),
                    usbSerialPortParameters.getDataBits(), usbSerialPortParameters.getStopBits(),
                    usbSerialPortParameters.getParity());

        } catch (IOException e) {
//            e.printStackTrace();
            Log.e(TAG, "Error setting up device");

            try {
                usbSerialPort.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            usbSerialPort = null;
            return;

        }

        // connected
        onDeviceStateChange();
        setConnected(true);
    }

//    Reset serial i/o manager in case that the state has changed
    private void onDeviceStateChange() {
        stopIOManager();
        startIOManager();
    }

    private void startIOManager() {
        if (usbSerialPort != null){
            Log.i(TAG, "startIOManager");

//            serialInputOutputManager = new SerialInputOutputManager(usbSerialPort,
//                    serialInputOutputManagerListener);
            serialInputOutputManager = new SerialInputOutputManagerWithQueue(usbSerialPort,
                    serialInputOutputManagerListener);

            executorService.submit(serialInputOutputManager);
        }
    }

    private void stopIOManager() {
        if (serialInputOutputManager != null){
            Log.i(TAG, "stopIOManager");
            serialInputOutputManager.stop();
            serialInputOutputManager = null;
        }
    }

//    Set device port to connect
    public void setUsbSerialPort(UsbSerialPort usbSerialPort) {
        this.usbSerialPort = usbSerialPort;
    }

//    Set parameter for connection
    public void setUsbSerialPortParameters(UsbSerialPortParameters usbSerialPortParameters) {
        this.usbSerialPortParameters = usbSerialPortParameters;
    }


    @Override
    public void sendData(byte[] data) {
//        throw new UnsupportedOperationException();

//        UsbSerialProber usbSerialProber = new UsbSerialProber(UsbSerialProber.getDefaultProbeTable());
//        UsbManager usbManager = (UsbManager) activity.getSystemService(Context.USB_SERVICE);
//        UsbDevice usbDevice = usbSerialPort.getDriver().getDevice();
//        UsbSerialDriver serialDriver = usbSerialProber.probeDevice(usbDevice);
//
//        serialDriver.write(data);

        serialInputOutputManager.writeAsync(data);



    }




}
