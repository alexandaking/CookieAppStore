package com.alexandaking.myappstore.bean;

import java.util.List;

/**
 * Created by alexandaking on 2017/12/13.
 */

public class CategorySubscribeBean {
    List<AppBean> appBeanList ;

    public CategorySubscribeBean(List<AppBean> appBeanList) {
        this.appBeanList = appBeanList;
    }

    public List<AppBean> getAppBeanList() {
        return appBeanList;
    }
}
