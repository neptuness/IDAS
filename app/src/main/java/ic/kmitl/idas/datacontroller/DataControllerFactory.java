package ic.kmitl.idas.datacontroller;

import android.app.Activity;

import com.hoho.android.usbserial.driver.UsbSerialPort;


public class DataControllerFactory {

    public static AbstractDataController createDataController(Activity activity){
        UsbSerialPortParameters usbParam = new UsbSerialPortParameters();
        return new ArduinoDataController(activity, usbParam);
    }

    public static ArduinoDataController createUsbDataController(Activity activity, UsbSerialPort usbSerialPort){
        UsbSerialPortParameters usbParam = new UsbSerialPortParameters();
        ArduinoDataController arduinoDataController = new ArduinoDataController(activity, usbParam);
        arduinoDataController.setUsbSerialPort(usbSerialPort);
        return arduinoDataController;
    }

}
