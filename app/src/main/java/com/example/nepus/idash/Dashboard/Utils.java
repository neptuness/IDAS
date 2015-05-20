package com.example.nepus.idash.Dashboard;

import android.content.res.Resources;

//this is a classs to convert unit
public class Utils {
    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return  (int) (dp * scale + 0.5f);
    }

    public static float sp2px(Resources resources, float sp){
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return (int) sp * scale;
    }
}
