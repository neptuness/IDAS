package com.example.nepus.idash;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by NEPUS on 5/17/15 AD.
 */

public class WidgetButton extends Button {
    public WidgetButton(Context context) {
        super(context);
    }

    public WidgetButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WidgetButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public WidgetButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);

    }
}
