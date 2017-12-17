package com.alexandaking.myappstore.mvp.presenter;


import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.mvpbase.BasePresenter;
import com.alexandaking.myappstore.mvp.view.view.AppMoreRecommendView;

/**
 * Created by alexandaking on 2017/12/13.
 */

public interface AppMoreRecommendPresenter extends BasePresenter<AppMoreRecommendView> {

    void getAppMoreRecommendData(BaseActivity activity, String type, String packageName);
}
