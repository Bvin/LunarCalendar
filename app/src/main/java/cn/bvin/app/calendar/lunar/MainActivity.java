package cn.bvin.app.calendar.lunar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvTips = (TextView) findViewById(R.id.tv_tips);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendar_view);
        calendarView.setOnDateSelectedListener(new CalendarView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Calendar calendar, boolean isClick) {
                //做时间换算

                java.util.Calendar curCalendar = java.util.Calendar.getInstance();
                int curDay = curCalendar.get(java.util.Calendar.DAY_OF_YEAR);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
                try {
                    Date date = sdf.parse(calendar.getYear()+"-"+calendar.getMonth()+"-"+calendar.getDay());
                    java.util.Calendar theCalendar = java.util.Calendar.getInstance();
                    theCalendar.setTime(date);
                    int theDay = theCalendar.get(java.util.Calendar.DAY_OF_YEAR);
                    int offset = theDay - curDay;
                    if (offset<30) {//几周几天
                        Log.d("the", curDay + ": " + theDay + ">" + (offset / 7) + ">>" + (offset % 7));
                        int offsetOfWeek = offset / 7;
                        int remainderDay = offset % 7;
                        tvTips.setText("距彼日"+offsetOfWeek+"周又"+remainderDay+"日");
                    }else {
                        int curMonth = curCalendar.get(java.util.Calendar.MONTH);
                        int theMonth = theCalendar.get(java.util.Calendar.MONTH);

                        tvTips.setText("距彼日"+(theMonth-curMonth)+"月");
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
