package com.alexandaking.myappstore.mvp.presenter.impl;

import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.mvpbase.BasePresenterImpl;
import com.alexandaking.myappstore.bean.CategoryNecessaryBean;
import com.alexandaking.myappstore.mvp.interactor.CategoryNecessaryInteractor;
import com.alexandaking.myappstore.mvp.presenter.CategoryNecessaryPresenter;
import com.alexandaking.myappstore.mvp.view.view.CategoryNecessaryView;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/12/13.
 */

public class CategoryNecessaryPresenterImpl extends BasePresenterImpl<CategoryNecessaryView> implements CategoryNecessaryPresenter {


    @Inject
    public CategoryNecessaryInteractor categoryNecessaryInteractor ;

    @Inject
    public CategoryNecessaryPresenterImpl(){

    }

    @Override
    public void getCategoryNecessaryData(BaseActivity activity) {
        categoryNecessaryInteractor.loadCategoryNecessaryData(activity, new IGetDataDelegate<CategoryNecessaryBean>() {
            @Override
            public void getDataSuccess(CategoryNecessaryBean categoryNecessaryBean) {
                mPresenterView.onCategoryNecessaryDataSuccess(categoryNecessaryBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCategoryNecessaryDataError(errmsg);
            }
        });
    }
}
