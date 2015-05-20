package com.example.nepus.idash.Notification;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ic.kmitl.idas.notification.IDASNotification;
import ic.kmitl.idas.notification.IDASNotificationSystem;
import ic.kmitl.idas.notification.IDASNotificationView;

//Adapter for list view designed for Nofication fragment
//Using observer pattern to observer IDAS notification system
public class NotificationListViewAdapter extends BaseAdapter implements Observer{

    private final Context c;
//    Use List to keep reference to keep all views
//    Experince in showing wrong view by returning convertView in getView()
    private List<IDASNotificationView> views = new ArrayList<>();

    public NotificationListViewAdapter(Context c, IDASNotificationSystem notiSystem) {
        notiSystem.addObserver(this);
        this.c = c;
        reload(notiSystem);
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public Object getItem(int position) {
        return views.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return views.get(position);
    }

//    Observer interface overriding
    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof IDASNotificationSystem){
            IDASNotificationSystem notiSystem = (IDASNotificationSystem) observable;
            reload(notiSystem);
        }
    }

//    Recreate all the notification view using new data from IDAS notification system
    private void reload(IDASNotificationSystem notiSystem){
        views.clear();
        List<IDASNotification> notificationList = notiSystem.getNotificationList();
        for (IDASNotification notification : notificationList) {
            IDASNotificationView view = new IDASNotificationView(c, notification);
            views.add(view);
        }
        notifyDataSetChanged();
    }

}
