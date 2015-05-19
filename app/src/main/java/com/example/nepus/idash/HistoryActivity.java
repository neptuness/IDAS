package com.example.nepus.idash;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.Toast;

import com.telerik.android.common.Function;
import com.telerik.widget.calendar.CalendarSelectionMode;
import com.telerik.widget.calendar.RadCalendarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class HistoryActivity extends Activity {

    private List<String> listOfDate = new ArrayList<>();
    private boolean colorCalendar = true;


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
//
//               cal.setFocusedMonthDateColor(Color.RED);
//                Toast.makeText(getBaseContext(),"Selected Date is\n\n"
//                                +dayOfMonth+" : "+month+" : "+year ,
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

        final RadCalendarView calendarView = (RadCalendarView)findViewById(R.id.calendarView);
        final Calendar calendar = Calendar.getInstance();
        calendarView.setDateToColor(new Function<Long, Integer>() {
            @Override
            public Integer apply(Long aLong) {
                calendar.setTimeInMillis(aLong);
//
//
//
////                int tempDate = Integer.parseInt(listOfDate.get(0).substring(0, 3));
////                Toast.makeText( getBaseContext(),listOfDate.get(0).substring(0, 3), Toast.LENGTH_LONG).show();
                if (colorCalendar) {
                    GetAvailableDateList();
                   colorCalendar = false;
                }
                for (String each : listOfDate) {

                    int tempYear = Integer.parseInt(each.substring(0, 4));
                    int tempMonth = Integer.parseInt(each.substring(4, 6));
                    int tempDate = Integer.parseInt(each.substring(6, 8));

//                    Toast.makeText( getBaseContext(),each, Toast.LENGTH_LONG).show();

                    if (calendar.get(Calendar.DAY_OF_MONTH) == tempDate && calendar.get(Calendar.MONTH) == tempMonth - 1 && calendar.get(Calendar.YEAR) == tempYear) {
                        return Color.RED;
                    }
                }
//                colorCalendar = false;
//                    if (calendar.get(Calendar.DAY_OF_MONTH) == 20 &&
//                            calendar.get(Calendar.MONTH) == 0 && calendar.get(Calendar.YEAR) == 2015) {
//
//                        return Color.GREEN;
//                    }

                return null;
            }
        });


        calendarView.setSelectionMode(CalendarSelectionMode.Single);
        calendarView.setOnSelectedDatesChangedListener(new RadCalendarView.
                OnSelectedDatesChangedListener() {
            @Override
            public void onSelectedDatesChanged(RadCalendarView.SelectionContext context) {

                List<String> dateSelected = Arrays.asList((String.format("%tF", context.newSelection().get(0))).split("\\s*-\\s*"));

                Toast.makeText(getApplicationContext(), dateSelected.toString(), Toast.LENGTH_SHORT).show();


                for (String each : listOfDate) {

                    int tempYear = Integer.parseInt(each.substring(0, 4));
                    int tempMonth = Integer.parseInt(each.substring(4, 6));
                    int tempDate = Integer.parseInt(each.substring(6, 8));


                    if (Integer.parseInt(dateSelected.get(2)) == tempDate && Integer.parseInt(dateSelected.get(1)) == tempMonth && Integer.parseInt(dateSelected.get(0)) == tempYear) {
//                        ReadFromText();

                        //dataList = Arrays.asList(dataEachDay.split("\\s*NewData\\s*"));
//                         Log.i("TestingX",mockupData);
                        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                        intent.putExtra(MapsActivity.DATE,  mockupData);
                        startActivity(intent);
                    }
                }

//                Toast.makeText(getApplicationContext(),
//                        String.format("%tF", context.newSelection().get(0)),
//                        Toast.LENGTH_SHORT).show();
//               Toast.makeText(getApplicationContext(), String.format("%tF", context.newSelection().get(0)),Toast.LENGTH_SHORT).show();

//                GetAvailableDateList();
//                  GetAvailableByDate();
            }
        });

    }
//
//    public void ReadFromText()
//    {
//        List<String> text = readRawTextFile(this, R.raw.data20150517);
//        Log.i("TestingX", text.toString());
//        C:\Users\Nil\Documents\IDAS
//        "C:\\java\\thaicreate.txt";
//        String path = "C:/Users/Nil/Documents/IDAS/20150517.txt";
//        File file = new File(path);
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            String line;
//            while ((line = br.readLine()) != null) {
//                dataEachDay += line;
//                Log.i("TestingX", line);
//            }
//            br.close();
//        } catch (IOException e) {
//
//            e.printStackTrace();
//            Log.i("TestingX", String.valueOf(e));
//        }
//    }
//    public void GetAvailableByDate()
//    {
//        String Test = "asdasdsagrgesfasdasNewDataasdsadsdmaskdmasdasasdasdNewDataasdsjadmsslkdf";
//        dataEachDay.clear();
//        dataEachDay = Arrays.asList(Test.split("\\s*NewData\\s*"));

//        Toast.makeText( getBaseContext(),dataEachDay.toString(), Toast.LENGTH_LONG).show();

//    }


    public void GetAvailableDateList()
    {
        String temp = "2015051520150517";
//        List<String> ListOfDate= new ArrayList<>();
        int index = 0;
        while (index<temp.length()) {
            listOfDate.add(temp.substring(index, Math.min(index + 8,temp.length())));
            index = index + 8;
        }

//        Toast.makeText( getBaseContext(),listOfDate.toString(), Toast.LENGTH_LONG).show();
//        return ListOfDate;
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

//    public static List<String> readRawTextFile(Context ctx, int resId)
//    {
//        InputStream inputStream = ctx.getResources().openRawResource(resId);
//
//        InputStreamReader inputreader = new InputStreamReader(inputStream);
//        BufferedReader buffreader = new BufferedReader(inputreader);
//        String line;
//        StringBuilder text = new StringBuilder();
//        ArrayList<String> lines = new ArrayList<String>();
//        try {
//            while (( line = buffreader.readLine()) != null) {
////                text.append(line);
////                text.append('\n');
//                lines.add(line);
//            }
//
//        } catch (IOException e) {
//            return null;
//        }
//        return lines;
////        return text.toString();
//    }

    private String mockupData = "New Data\n" +
            "13.729670, 100.775450, 35, 9, 0.00\n" +
            "13.729670, 100.775450, 35, 14, 0.00\n" +
            "13.729670, 100.775450, 35, 19, 0.00\n" +
            "13.729670, 100.775450, 35, 24, 0.00\n" +
            "13.729670, 100.775450, 35, 29, 0.00\n" +
            "13.729670, 100.775450, 35, 34, 0.00\n" +
            "13.729670, 100.775450, 35, 39, 0.00\n" +
            "13.729670, 100.775450, 35, 44, 0.00\n" +
            "13.729670, 100.775450, 35, 49, 0.00\n" +
            "13.729670, 100.775450, 35, 54, 0.00\n" +
            "13.729670, 100.775450, 35, 59, 0.00\n" +
            "13.729710, 100.775490, 36, 4, 0.00\n" +
            "13.729650, 100.775770, 36, 9, 0.00\n" +
            "13.729600, 100.776090, 36, 14, 0.00\n" +
            "13.729580, 100.776390, 36, 19, 0.00\n" +
            "13.729570, 100.776540, 36, 24, 0.00\n" +
            "13.729570, 100.776660, 36, 29, 0.00\n" +
            "13.729550, 100.776920, 36, 34, 0.00\n" +
            "13.729560, 100.777080, 36, 39, 0.00\n" +
            "13.729620, 100.777300, 36, 44, 0.00\n" +
            "13.729690, 100.777580, 36, 49, 0.00\n" +
            "13.729930, 100.777610, 36, 54, 0.00\n" +
            "13.730190, 100.777630, 36, 59, 0.00\n" +
            "13.730230, 100.777900, 37, 4, 0.00\n" +
            "13.730180, 100.778080, 37, 9, 0.00\n" +
            "13.729970, 100.778130, 37, 14, 0.00\n" +
            "13.729620, 100.778130, 37, 19, 0.00\n" +
            "13.729630, 100.778290, 37, 24, 0.00\n" +
            "13.730060, 100.778660, 37, 29, 0.00\n" +
            "13.730390, 100.778980, 37, 34, 0.00\n" +
            "13.730590, 100.779530, 37, 39, 0.00\n" +
            "13.730550, 100.780300, 37, 44, 0.00\n" +
            "13.730520, 100.781140, 37, 49, 0.00\n" +
            "13.730510, 100.782030, 37, 54, 0.00\n" +
            "13.730520, 100.782880, 37, 59, 0.00\n" +
            "13.730510, 100.783510, 38, 4, 0.00\n" +
            "13.730670, 100.783860, 38, 9, 0.00\n" +
            "13.731120, 100.783980, 38, 14, 0.00\n" +
            "13.731660, 100.784010, 38, 19, 0.00\n" +
            "13.731900, 100.784050, 38, 24, 0.00\n" +
            "13.731960, 100.783960, 38, 29, 0.00\n" +
            "13.731960, 100.783440, 38, 34, 0.00\n" +
            "13.731940, 100.782550, 38, 39, 0.00\n" +
            "13.731920, 100.781380, 38, 44, 0.00\n" +
            "13.731890, 100.780070, 38, 49, 0.00\n" +
            "13.731850, 100.778690, 38, 54, 0.00\n" +
            "13.731820, 100.777250, 38, 59, 0.00\n" +
            "13.731820, 100.775800, 39, 4, 0.00\n" +
            "13.731810, 100.774410, 39, 9, 0.00\n" +
            "13.731810, 100.772980, 39, 14, 0.00\n" +
            "13.731790, 100.771550, 39, 19, 0.00\n" +
            "13.731720, 100.770230, 39, 24, 0.00\n" +
            "13.731650, 100.769050, 39, 29, 0.00\n" +
            "13.731580, 100.767860, 39, 34, 0.00\n" +
            "13.731540, 100.766590, 39, 39, 0.00\n" +
            "13.731490, 100.765400, 39, 44, 0.00\n" +
            "13.731250, 100.764220, 39, 49, 0.00\n" +
            "13.730940, 100.763030, 39, 54, 0.00\n" +
            "13.730640, 100.761920, 39, 59, 0.00\n" +
            "13.730310, 100.760950, 40, 4, 0.00\n" +
            "13.730040, 100.760370, 40, 9, 0.00\n" +
            "13.729560, 100.760120, 40, 14, 0.00\n" +
            "13.728970, 100.760210, 40, 19, 0.00\n" +
            "13.728480, 100.760280, 40, 24, 0.00\n" +
            "13.728240, 100.759950, 40, 29, 0.00\n" +
            "13.728360, 100.759320, 40, 34, 0.00\n" +
            "13.728910, 100.758980, 40, 39, 0.00\n" +
            "13.729380, 100.758590, 40, 44, 0.00\n" +
            "13.729450, 100.757860, 40, 49, 0.00\n" +
            "13.729440, 100.757010, 40, 54, 0.00\n" +
            "13.729430, 100.756070, 40, 59, 0.00\n" +
            "13.729400, 100.755070, 41, 4, 0.00\n" +
            "13.729390, 100.754130, 41, 9, 0.00\n" +
            "13.729370, 100.753200, 41, 14, 0.00\n" +
            "13.729340, 100.752150, 41, 19, 0.00\n" +
            "13.729270, 100.751110, 41, 24, 0.00\n" +
            "13.728850, 100.750210, 41, 29, 0.00\n" +
            "13.728190, 100.749430, 41, 34, 0.00\n" +
            "13.727930, 100.748580, 41, 39, 0.00\n" +
            "13.727970, 100.747740, 41, 44, 0.00\n" +
            "13.727960, 100.747150, 41, 49, 0.00\n" +
            "13.727830, 100.746860, 41, 54, 0.00\n" +
            "13.727640, 100.746810, 41, 59, 0.00\n" +
            "13.727460, 100.746800, 42, 4, 0.00\n" +
            "13.727100, 100.746750, 42, 9, 0.00\n" +
            "13.726730, 100.746760, 42, 14, 0.00\n" +
            "13.726350, 100.746740, 42, 19, 0.00\n" +
            "13.725940, 100.746730, 42, 24, 0.00\n" +
            "13.725670, 100.746630, 42, 29, 0.00\n" +
            "13.725210, 100.746600, 42, 34, 0.00\n" +
            "13.724490, 100.746630, 42, 39, 0.00\n" +
            "13.723660, 100.746640, 42, 44, 0.00\n" +
            "13.723000, 100.746640, 42, 49, 0.00\n" +
            "13.722750, 100.746640, 42, 54, 0.00\n" +
            "13.722610, 100.746790, 42, 59, 0.00\n" +
            "13.722620, 100.747420, 43, 4, 0.00\n" +
            "13.722630, 100.748240, 43, 9, 0.00\n" +
            "13.722680, 100.749120, 43, 14, 0.00\n" +
            "13.722850, 100.750040, 43, 19, 0.00\n" +
            "13.723100, 100.750930, 43, 24, 0.00\n" +
            "13.723100, 100.751620, 43, 29, 0.00\n" +
            "13.722750, 100.752160, 43, 34, 0.00\n" +
            "13.722420, 100.752800, 43, 39, 0.00\n" +
            "13.722160, 100.753700, 43, 44, 0.00\n" +
            "13.721890, 100.754710, 43, 49, 0.00\n" +
            "13.721640, 100.755690, 43, 54, 0.00\n" +
            "13.721658, 100.756707, 43, 59, 0.00\n" +


            "New Data\n" +
            "13.721658, 100.756707, 49, 56, 0.00\n" +
            "13.721661, 100.757472, 49, 1, 0.00\n" +
            "13.721665, 100.759070, 49, 6, 0.00\n" +
            "13.721729, 100.762026, 49, 11, 0.00\n" +
            "13.721781, 100.764724, 49, 16, 0.00\n" +
            "13.721828, 100.767090, 50, 21, 0.00\n" +
            "13.721849, 100.770201, 50, 16, 0.00\n" +
            "13.721880, 100.772089, 50, 16, 0.00\n" +
            "13.721891, 100.773924, 50, 16, 0.00\n" +
            "13.721938, 100.776708, 50, 16, 0.00\n" +
            "13.721990, 100.778698, 51, 16, 0.00\n" +
            "13.722031, 100.780195, 51, 16, 0.00\n" +
            "13.723945, 100.780146, 51, 16, 0.00\n" +
            "13.724789, 100.780130, 51, 16, 0.00\n" +
            "13.724826, 100.778188, 52, 16, 0.00\n" +
            "13.727431, 100.778006, 52, 16, 0.00\n" +
            "13.728953, 100.777968, 52, 16, 0.00\n" +
            "13.729604, 100.778108, 53, 16, 0.00\n" ;

}
