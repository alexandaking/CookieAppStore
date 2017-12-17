package com.alexandaking.myappstore.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by alexandaking on 2017/12/15.
 */

public class AppInfo {
    private String name ;
    private String packageName ;
    private Drawable icon ;
    private long firstInstallTime ;
    private String versionName ;

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public long getFirstInstallTime() {
        return firstInstallTime;
    }

    public String getVersionName() {
        return versionName;
    }

    public AppInfo(String name, String packageName, Drawable icon, long firstInstallTime, String versionName) {

        this.name = name;
        this.packageName = packageName;
        this.icon = icon;
        this.firstInstallTime = firstInstallTime;
        this.versionName = versionName;
    }
}
