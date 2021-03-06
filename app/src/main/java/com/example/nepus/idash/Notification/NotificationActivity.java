package com.example.nepus.idash.Notification;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nepus.idash.R;

import ic.kmitl.idas.notification.IDASNotification;
import ic.kmitl.idas.notification.IDASNotificationSystem;


public class NotificationActivity extends Activity {

//    private static final String TAG = NotificationActivity.class.getSimpleName();
//    private Fragment notificationFragment;
//    private TestCommunication notificationFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        if (savedInstanceState == null) {
//            notificationFragment = new NotificationFragment();
//            notificationFragment = new TestCommunication();
//            getFragmentManager().beginTransaction().add(R.id.container, notificationFragment).commit();
            getFragmentManager().beginTransaction().add(R.id.container, new NotificationFragment()).commit();

        }
        ActionBar actionBar = getActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notification, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


//        Create dummy notification for demonstration
        if (id == R.id.action_dummynotification) {
            IDASNotificationSystem notiSystem = IDASNotificationSystem.getInstance();
            IDASNotification notification = new IDASNotification(notiSystem.getAvailableNotificationId(), "IDASNotification", "dummy content");
            notification.setAutoCancel(true);
            notification.setHeads_up(true);
            notiSystem.addNotification(getApplicationContext(), notification);
            return true;
        }
        else if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        mHandler.sendEmptyMessage(MESSAGE_REFRESH);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mHandler.removeMessages(MESSAGE_REFRESH);
//    }
//
//    private static final int MESSAGE_REFRESH = 101;
//    private static final long REFRESH_TIMEOUT_MILLIS = 5000;
//
//    //    use mHandler to refresh device list periodically
////    mHandler will be called from onResume and continue calling it self periodically
//    private final Handler mHandler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case MESSAGE_REFRESH:
//                    refreshDeviceList();
//                    mHandler.sendEmptyMessageDelayed(MESSAGE_REFRESH, REFRESH_TIMEOUT_MILLIS);
//                    break;
//                default:
//                    super.handleMessage(msg);
//                    break;
//            }
//        }
//
//    };
//
//    private void refreshDeviceList() {
//        new AsyncTask<Void, Void, List<UsbSerialPort>>() {
//            @Override
//            protected List<UsbSerialPort> doInBackground(Void... params) {
//                Log.d(TAG, "Refreshing device list ...");
//                SystemClock.sleep(1000);
//
//
//                UsbManager mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
//                final List<UsbSerialDriver> drivers =
//                        UsbSerialProber.getDefaultProber().findAllDrivers(mUsbManager);
//
//                final List<UsbSerialPort> result = new ArrayList<UsbSerialPort>();
//                for (final UsbSerialDriver driver : drivers) {
//                    final List<UsbSerialPort> ports = driver.getPorts();
//                    Log.d(TAG, String.format("+ %s: %s port%s",
//                            driver, Integer.valueOf(ports.size()), ports.size() == 1 ? "" : "s"));
//                    result.addAll(ports);
//                }
//
//                return result;
//            }
//
//            @Override
//            protected void onPostExecute(List<UsbSerialPort> result) {
//                if (result.isEmpty())
//                    return;
//
//
//                Log.i(TAG, result.toString());
//                if (result.size() == 1){
//
//                    abstractDataController = DataControllerFactory.createUsbDataController(NotificationActivity.this, result.get(0));
//                    abstractDataController.setDataReceiver(dataReceiverListener);
//                    abstractDataController.connect();
//
//                    notificationFragment.setDataController(abstractDataController);
//                    mHandler.removeMessages(MESSAGE_REFRESH);
//                }
//
//            }
//
//        }.execute((Void) null);
//    }
//
//    //    call abstractDataController.sendData(data) to send data
////    use below code to convert data from string to byte[]
////    byte[] data = stringData.getBytes(Charset.forName("UTF-8"));
////    call abstractDataController.isConnected() to check if the connection is established successfully
//    private AbstractDataController abstractDataController = null;
//
//    //    DataReceiver of AbstractDataController
//    private DataReceiver dataReceiverListener = new DataReceiver() {
//        @Override
//        public void onDataReceive(byte[] data) {
//
//            String stringData = null;
//            try {
//                stringData = new String(data, "UTF-8");
//                notificationFragment.appendTextResult(stringData);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//
//            Log.i(TAG, stringData);
//
//        }
//    };


}
