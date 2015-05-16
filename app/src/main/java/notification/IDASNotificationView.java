package notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nepus.idash.R;

import notification.exception.IDASNotificationNotFoundException;


public class IDASNotificationView extends RelativeLayout {
    private IDASNotification notification;
    public IDASNotificationView(Context context, IDASNotification notification) {
        super(context);
        setNotification(notification);
    }

    public void setNotification(IDASNotification notification) {
        this.notification = notification;
        setupLayout();
    }

    private void setupLayout() {
        ViewGroup layout = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.layout_notification, this);
        TextView tv = (TextView) layout.findViewById(R.id.detailTextView);
        Button deleteBtn = (Button) layout.findViewById(R.id.deleteBtn);

//        tv.setText(notification.toString());
        tv.setText(notification.getContentText());
        deleteBtn.setOnClickListener(deleteBtnOnClickListener);

    }

    private OnClickListener deleteBtnOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = notification.getId();
            IDASNotificationSystem notificationSystem = IDASNotificationSystem.getInstance();
            try {
                notificationSystem.removeNotification(getContext(), id);
            } catch (IDASNotificationNotFoundException e) {
                e.printStackTrace();
            }
        }
    };


}
