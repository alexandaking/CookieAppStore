package com.alexandaking.myappstore.mvp.view.view;

import com.alexandaking.myappstore.base.mvpbase.BaseView;
import com.alexandaking.myappstore.bean.AppRecommendBean;

/**
 * Created by alexandaking on 2017/12/12.
 */

public interface AppRecommendFragmentView extends BaseView {

    void onAppRecommendDataSuccess(AppRecommendBean appRecommendBean);
    void onAppRecommendDataError(String msg);
}
