package com.alexandaking.myappstore.mvp.presenter;


import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.mvpbase.BasePresenter;
import com.alexandaking.myappstore.mvp.view.view.AppRecommendFragmentView;

/**
 * Created by alexandaking on 2017/12/12.
 */

public interface AppRecommendFragmentPresenter extends BasePresenter<AppRecommendFragmentView> {

    void getAppRecommendData(BaseActivity activity, String packageName);
}
