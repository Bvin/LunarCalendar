package cn.bvin.app.calendar.lunar.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.haibin.calendarview.WeekBar;

import cn.bvin.app.calendar.lunar.R;

/**
 * Created by Administrator on 2018/5/30.
 */

public class LunarWeekBar extends WeekBar {
    public LunarWeekBar(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.lunar_week_bar, this, true);
    }

    @Override
    protected void onWeekStartChange(int weekStart) {
        for (int i = 0; i < getChildCount(); i++) {
            ((TextView) getChildAt(i)).setText(getWeekString(i, weekStart));
        }
    }

    private String getWeekString(int index, int weekStart) {
        String[] weeks = getContext().getResources().getStringArray(R.array.lunar_week_string_array);

        if (weekStart == 1) {
            return weeks[index];
        }
        if (weekStart == 2) {
            return weeks[index == 6 ? 0 : index + 1];
        }
        return weeks[index == 0 ? 6 : index - 1];
    }
}
