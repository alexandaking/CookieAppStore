package com.alexandaking.myappstore.mvp.presenter.impl;

import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.mvpbase.BasePresenterImpl;
import com.alexandaking.myappstore.bean.AppIntroductionBean;
import com.alexandaking.myappstore.mvp.interactor.AppIntroductionInteractor;
import com.alexandaking.myappstore.mvp.presenter.AppIntroductionFragmentPresenter;
import com.alexandaking.myappstore.mvp.view.view.AppIntroductionFragmentView;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/12/9.
 */

public class AppIntroductionPresenterImpl extends BasePresenterImpl<AppIntroductionFragmentView> implements AppIntroductionFragmentPresenter {

    @Inject
    public AppIntroductionInteractor appIntroductionInteractor;
    @Inject
    public AppIntroductionPresenterImpl(){

    }

    @Override
    public void getAppIntroductionData(BaseActivity activity, String packageName) {
        appIntroductionInteractor.loadAppIntroductionData(activity, packageName, new IGetDataDelegate<AppIntroductionBean>() {
            @Override
            public void getDataSuccess(AppIntroductionBean appIntroductionBean) {
                mPresenterView.onAppIntroductionDataSuccess(appIntroductionBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onAppIntroductionDataError(errmsg);
            }
        });
    }
}
