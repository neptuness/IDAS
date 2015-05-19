package com.example.nepus.idash;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import ic.kmitl.idas.notification.IDASNotificationSystem;

public class NotificationFragment extends Fragment {

    public NotificationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notification, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        IDASNotificationSystem notiSystem = IDASNotificationSystem.getInstance();

////        mock data
//
//        for (int i=0; i<10; ++i){
//            int id = notiSystem.getAvailableNotificationId();
//            IDASNotification notification = new IDASNotification(id, "title " + i, "" + id);
//            notification.setHeads_up(true);
//            notiSystem.addNotification(getActivity(), notification);
//        }
////        end mock data

        NotificationListViewAdapter adapter = new NotificationListViewAdapter(getActivity(), notiSystem);

        listView.setAdapter(adapter);


        return rootView;
    }





}
