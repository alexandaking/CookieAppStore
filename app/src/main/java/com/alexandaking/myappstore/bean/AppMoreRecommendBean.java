package com.alexandaking.myappstore.bean;

import java.util.List;

/**
 * Created by alexandaking on 2017/12/13.
 */

public class AppMoreRecommendBean {

    private List<AppBean> moreAppBean ;

    public AppMoreRecommendBean(List<AppBean> moreAppBean) {
        this.moreAppBean = moreAppBean;
    }

    public List<AppBean> getMoreAppBean() {
        return moreAppBean;
    }
}
