package com.alexandaking.myappstore.mvp.presenter.impl;

import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.mvpbase.BasePresenterImpl;
import com.alexandaking.myappstore.bean.CategoryBean;
import com.alexandaking.myappstore.bean.RecommendBean;
import com.alexandaking.myappstore.mvp.interactor.CategoryInteractor;
import com.alexandaking.myappstore.mvp.interactor.RecommendInteractor;
import com.alexandaking.myappstore.mvp.presenter.CategoryFragmentPresenter;
import com.alexandaking.myappstore.mvp.presenter.RecommendFragmentPresenter;
import com.alexandaking.myappstore.mvp.view.view.CategoryFragmentView;
import com.alexandaking.myappstore.mvp.view.view.RecommendFragmentView;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/10/25.
 */

public class CategoryPresenterImpl extends BasePresenterImpl<CategoryFragmentView> implements CategoryFragmentPresenter {


    @Inject
    public CategoryInteractor categoryInteractor;

    @Inject
    public CategoryPresenterImpl(){

    }

    @Override
    public void getCategoryData(BaseActivity activity) {
        categoryInteractor.loadCategoryData(activity, new IGetDataDelegate<CategoryBean>() {
            @Override
            public void getDataSuccess(CategoryBean categoryBean) {
                mPresenterView.onCategoryDataSuccess(categoryBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCategoryDataFailure(errmsg);
            }
        });
    }
}
