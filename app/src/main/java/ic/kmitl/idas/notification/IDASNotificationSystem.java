package ic.kmitl.idas.notification;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import ic.kmitl.idas.notification.exception.IDASNotificationNotFoundException;

//Notification management system for IDAS application
public class IDASNotificationSystem extends Observable{
//    Used in Log for application debugging
    private static final String TAG = IDASNotificationSystem.class.getSimpleName();
//    Keep all the notification for management
    private List<IDASNotification> notificationPool;
//    Only single instance is available in the system(Singleton Pattern)
    private static IDASNotificationSystem instance = null;

    private IDASNotificationSystem() {
        notificationPool = new ArrayList<>();
    }

//    Use synchronized to make thread-safe singleton
    public synchronized static IDASNotificationSystem getInstance(){
        if (instance == null)
            instance = new IDASNotificationSystem();
        return instance;
    }

    public void addNotification(Context context, IDASNotification notificationWrapper){
        if (!contain(notificationWrapper.getId())) {
            notificationPool.add(notificationWrapper);
            setChanged();
            notifyObservers();
        }
        notificationWrapper.show(context);
        Log.d(TAG, "Notification added");
    }

    public IDASNotification removeNotification(Context context, int id) throws IDASNotificationNotFoundException {
        for (int i=0; i<notificationPool.size(); ++i){
            IDASNotification notification = notificationPool.get(i);
            if (notification.getId() == id){
                notificationPool.remove(i);
                notification.cancel(context);
                setChanged();
                notifyObservers();
                return notification;
            }
        }
        throw new IDASNotificationNotFoundException(id);
    }

    public boolean contain(int id){
        for (IDASNotification notification : notificationPool)
            if (notification.getId() == id)
                return true;
        return false;
    }

    public List<IDASNotification> getNotificationList() {
        return notificationPool;
    }

    public int getNotificationPoolSize(){
        return notificationPool.size();
    }

    public void removeAllNotification(Context context){
        for (IDASNotification notification : notificationPool)
            notification.cancel(context);
        notificationPool.clear();
        setChanged();
        notifyObservers();
        Log.d(TAG, "" + notificationPool.size());

    }

//    Randomly generate and check if the id is available
    public int getAvailableNotificationId(){
        Random random = new Random();
        int id;

        do {
            id = random.nextInt();
        }while (contain(id));

        return id;

    }

}
