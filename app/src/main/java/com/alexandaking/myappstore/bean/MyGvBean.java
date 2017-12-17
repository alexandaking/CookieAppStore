package com.alexandaking.myappstore.bean;

/**
 * Created by alexandaking on 2017/12/1.
 */

public class MyGvBean {

    private String name ;
    private int iconId ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconUrl(int iconId) {
        this.iconId = iconId;
    }

    public MyGvBean(String name, int iconId) {

        this.name = name;
        this.iconId = iconId;
    }
}
