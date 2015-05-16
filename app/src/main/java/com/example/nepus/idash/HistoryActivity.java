package com.example.nepus.idash;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import com.telerik.android.common.Function;
import com.telerik.widget.calendar.CalendarSelectionMode;
import com.telerik.widget.calendar.RadCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class HistoryActivity extends FragmentActivity {

    CalendarView cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

//
//        ListView listView = (ListView) findViewById(R.id.listView);
//        List<View> views = new ArrayList<View>();
//        for (int i=0; i<10; ++i){
//            Button btn = new Button(this);
//            btn.setText(""+i);
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Button btn = (Button) v;
//
//                    Toast.makeText(getApplicationContext(), btn.getText().toString(), Toast.LENGTH_SHORT).show();
//                }
//            });
//            views.add(btn);
//        }
//        ListAdaptor adapter = new ListAdaptor(views);
//        listView.setAdapter(adapter);

//        cal = (CalendarView) findViewById(R.id.calendarView);
//
//        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//
//
//            @Override
//            public void onSelectedDayChange(CalendarView view, int year, int month,
//                                            int dayOfMonth) {
//                // TODO Auto-generated method stub
//               cal.setFocusedMonthDateColor(Color.RED);
//                Toast.makeText(getBaseContext(),"Selected Date is\n\n"
//                                +dayOfMonth+" : "+month+" : "+year ,
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
        RadCalendarView calendarView = (RadCalendarView)findViewById(R.id.calendarView);

        final Calendar calendar = Calendar.getInstance();
        calendarView.setDateToColor(new Function<Long, Integer>() {
            @Override
            public Integer apply(Long aLong) {
                calendar.setTimeInMillis(aLong);
                if(calendar.get(Calendar.DAY_OF_MONTH) == 20 &&
                        calendar.get(Calendar.MONTH) == 0) {

                    return Color.RED;
                }
                return null;
            }
        });

        calendarView.setSelectionMode(CalendarSelectionMode.Single);
        calendarView.setOnSelectedDatesChangedListener(new RadCalendarView.
                OnSelectedDatesChangedListener() {
            @Override
            public void onSelectedDatesChanged(RadCalendarView.SelectionContext context) {
                Toast.makeText(getApplicationContext(),
                        String.format("%tF", context.newSelection().get(0)),
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
