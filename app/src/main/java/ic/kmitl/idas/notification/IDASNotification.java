package ic.kmitl.idas.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;

import com.example.nepus.idash.R;

//Simple form of notification model designed for IDAS application
public class IDASNotification {
//    Used in Log for application debugging
    private final static String TAG = IDASNotification.class.getSimpleName();

//    Header of the notificaton
    private String title;
//    Detail of the notification
    private String contentText;
//    Logo of the notification, launcher icon is set as default logo
    private int icon = R.mipmap.ic_launcher;
//    Id is used to specify after the notification is broadcast to the system
//    can be used for notification management
    private int id;
//    Notification will be cancel automatically if user click on
//    the notification(contentIntent need to be set)
    private boolean autoCancel = false;
//    Set to show as heads-up notification
    private boolean heads_up = false;
//    Intent to launch when user click on notification
    private PendingIntent contentIntent = null;
//    Intent to launch when user cancel the notification
    private PendingIntent deleteIntent = null;

    public IDASNotification(int id, String title, String contentText) {
        this.id = id;
        this.title = title;
        this.contentText = contentText;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public boolean isAutoCancel() {
        return autoCancel;
    }

    public void setAutoCancel(boolean autoCancel) {
        this.autoCancel = autoCancel;
    }

    public boolean isHeads_up() {
        return heads_up;
    }

    public void setHeads_up(boolean heads_up) {
        this.heads_up = heads_up;
    }

    public PendingIntent getContentIntent() {
        return contentIntent;
    }

    public void setContentIntent(PendingIntent contentIntent) {
        this.contentIntent = contentIntent;
    }

    public PendingIntent getDeleteIntent() {
        return deleteIntent;
    }

    public void setDeleteIntent(PendingIntent deleteIntent) {
        this.deleteIntent = deleteIntent;
    }

//    Construct and register notification to the system
    void show(Context c){

//        NotificationCompat.Builder builder = new NotificationCompat.Builder(c);
        Notification.Builder builder = new Notification.Builder(c);

        builder = builder.setAutoCancel(autoCancel);
        builder = builder.setSmallIcon(icon);
        builder = builder.setContentTitle(title);
        builder = builder.setContentText(contentText);
        if (heads_up) {
            builder = builder.setPriority(Notification.PRIORITY_HIGH);
            builder = builder.setVibrate(new long[0]);
        }

        if (contentIntent != null) builder.setContentIntent(contentIntent);
        if (deleteIntent != null) builder.setDeleteIntent(deleteIntent);


        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, notification);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (this.getClass() != o.getClass())
            return false;
        IDASNotification other;
        other = (IDASNotification) o;
        return this.getId() == other.getId();
    }

//    Remove notification from the system
    void cancel(Context context){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(id);
    }

    @Override
    public String toString() {
        return "IDASNotification{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contentText='" + contentText + '\'' +
                '}';
    }


}



