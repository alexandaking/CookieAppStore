package com.alexandaking.myappstore.mvp.view.view;

import com.alexandaking.myappstore.base.mvpbase.BaseView;
import com.alexandaking.myappstore.bean.AppMoreRecommendBean;

/**
 * Created by alexandaking on 2017/12/13.
 */

public interface AppMoreRecommendView extends BaseView {
    void onAppMoreRecommendDataSuccess(AppMoreRecommendBean appMoreRecommendBean);
    void onAppMoreRecommendDataError(String msg);
}
