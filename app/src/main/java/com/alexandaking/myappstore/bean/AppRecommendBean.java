package com.alexandaking.myappstore.bean;

import java.util.List;

/**
 * Created by alexandaking on 2017/12/12.
 */

public class AppRecommendBean {

    private List<AppBean> popularAppBeanList ;
    private List<AppBean> tasteAppBeanList ;
    private List<AppBean> hotAppBeanList ;

    public AppRecommendBean(List<AppBean> popularAppBeanList, List<AppBean> tasteAppBeanList, List<AppBean> hotAppBeanList) {
        this.popularAppBeanList = popularAppBeanList;
        this.tasteAppBeanList = tasteAppBeanList;
        this.hotAppBeanList = hotAppBeanList;
    }

    public List<AppBean> getPopularAppBeanList() {
        return popularAppBeanList;
    }

    public List<AppBean> getTasteAppBeanList() {
        return tasteAppBeanList;
    }

    public List<AppBean> getHotAppBeanList() {
        return hotAppBeanList;
    }
}
