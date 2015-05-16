package com.example.nepus.idash;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;


public class DashboardAdapter extends BaseAdapter{

    private Context mContext;
    private Button btns[];

    public DashboardAdapter(Context c,int num_pages) {
        mContext = c;
        btns = new Button[num_pages];

        for (int i=0; i<btns.length; ++i){
            Button btn = new WidgetButton(c);

            btn.setText("Add widget");
            btns[i] = btn;
            btns[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button btn = (Button) v;
                    Toast.makeText(v.getContext(), btn.getText(), Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

    public int getCount() {
        return btns.length;
    }

    public Object getItem(int position) {
        return btns[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) { return btns[position]; }
}


