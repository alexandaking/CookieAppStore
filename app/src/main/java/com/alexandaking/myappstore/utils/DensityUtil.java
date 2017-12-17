package com.alexandaking.myappstore.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by alexandaking on 2017/12/1.
 */

public class DensityUtil {

    private static DisplayMetrics displayMetrics;

    public static int getDensity (Context context, int density) {
        if (displayMetrics == null) {
            displayMetrics = new DisplayMetrics();
            if (context != null) {
                ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
            }
        }
        return (int)(density * displayMetrics.density);
    }
}
