package com.alexandaking.myappstore.mvp.presenter.impl;

import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.mvpbase.BasePresenter;
import com.alexandaking.myappstore.base.mvpbase.BasePresenterImpl;
import com.alexandaking.myappstore.bean.TopBean;
import com.alexandaking.myappstore.mvp.interactor.TopInteractor;
import com.alexandaking.myappstore.mvp.presenter.TopFragmentPresenter;
import com.alexandaking.myappstore.mvp.view.view.TopFragmentView;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/11/22.
 */

public class TopPresenterImpl extends BasePresenterImpl<TopFragmentView> implements TopFragmentPresenter {

    @Inject
    public TopInteractor topInteractor;

    @Inject
    public TopPresenterImpl(){

    }

    @Override
    public void getTopData(BaseActivity activity) {

        topInteractor.loadTopData(activity, new IGetDataDelegate<TopBean>() {
            @Override
            public void getDataSuccess(TopBean topBean) {
                mPresenterView.onCategoryDataSuccess(topBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCategoryDataFailure(errmsg);
            }
        });
    }
}
