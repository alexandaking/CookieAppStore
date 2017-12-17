package com.alexandaking.myappstore.bean;

import java.util.List;

/**
 * Created by alexandaking on 2017/12/13.
 */

public class CategoryNecessaryBean {

    private Head head ;
    private List<AppBean> appBeanList ;

    public CategoryNecessaryBean(Head head, List<AppBean> appBeanList) {
        this.head = head;
        this.appBeanList = appBeanList;
    }

    public Head getHead() {
        return head;
    }

    public List<AppBean> getAppBeanList() {
        return appBeanList;
    }

    public static class Head {
        private String icon ;
        private String intro ;

        public String getIcon() {
            return icon;
        }

        public String getIntro() {
            return intro;
        }

        public Head(String icon, String intro) {

            this.icon = icon;
            this.intro = intro;
        }
    }
}