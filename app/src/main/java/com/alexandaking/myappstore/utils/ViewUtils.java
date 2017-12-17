package com.alexandaking.myappstore.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by alexandaking on 2017/12/10.
 */

public class ViewUtils {
    public static void removeSelfFromParent(View v){
        if(v!=null){
            ViewParent parent = v.getParent();
            if(parent!=null && parent instanceof ViewGroup){
                ViewGroup group=(ViewGroup) parent;
                group.removeView(v);
            }
        }
    }
}
