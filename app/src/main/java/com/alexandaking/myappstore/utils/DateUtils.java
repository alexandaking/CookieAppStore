package com.alexandaking.myappstore.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alexandaking on 2017/12/15.
 */

public class DateUtils {
    public static String getDate(){
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy/MM/dd");
        Date date=new Date();
        return dateFormater.format(date);
    }

    public static String getDate(long time){
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy/MM/dd");
        Date date=new Date(time);
        return dateFormater.format(date);
    }
}
