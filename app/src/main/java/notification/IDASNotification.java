package notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.example.nepus.idash.R;


public class IDASNotification {

    private final static String TAG = IDASNotification.class.getSimpleName();

    private String title;
    private String contentText;
    private int icon = R.mipmap.ic_launcher;
    private int id;
    private boolean autoCancel = false;
    private boolean heads_up = false;
    private PendingIntent contentIntent = null;
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
        IDASNotification other = (IDASNotification) o;
        return this.getId() == other.getId();
    }

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



