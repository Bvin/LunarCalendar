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
                int weekOfYear = curCalendar.get(java.util.Calendar.WEEK_OF_YEAR);
                int weekOfMonth = curCalendar.get(java.util.Calendar.WEEK_OF_MONTH);
                Log.d("cur", weekOfYear+": "+weekOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
                try {
                    Date date = sdf.parse(calendar.getYear()+"-"+calendar.getMonth()+"-"+calendar.getDay());
                    java.util.Calendar theCalendar = java.util.Calendar.getInstance();
                    theCalendar.setTime(date);
                    int theWeekOfYear = theCalendar.get(java.util.Calendar.WEEK_OF_YEAR);
                    int theWeekOfMonth = theCalendar.get(java.util.Calendar.WEEK_OF_MONTH);
                    Log.d("the", theWeekOfYear+": "+theWeekOfMonth);
                    tvTips.setText(sdf.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
