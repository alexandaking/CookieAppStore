package com.alexandaking.myappstore.mvp.presenter.impl;

import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.mvpbase.BasePresenterImpl;
import com.alexandaking.myappstore.bean.CategoryToolBean;
import com.alexandaking.myappstore.mvp.interactor.CategoryToolActivityInteractor;
import com.alexandaking.myappstore.mvp.presenter.CategoryToolActivityPresenter;
import com.alexandaking.myappstore.mvp.view.view.CategoryToolActivityView;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/12/13.
 */

public class CategoryToolActivityPresenterImpl extends BasePresenterImpl<CategoryToolActivityView> implements CategoryToolActivityPresenter {

    @Inject
    public CategoryToolActivityInteractor categoryToolActivityInteractor ;

    @Inject
    public CategoryToolActivityPresenterImpl(){

    }

    @Override
    public void getCategoryToolData(BaseActivity activity) {
        categoryToolActivityInteractor.loadCategoryToolData(activity, new IGetDataDelegate<CategoryToolBean>() {
            @Override
            public void getDataSuccess(CategoryToolBean categoryToolBean) {
                mPresenterView.onCategoryToolDataSuccess(categoryToolBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCategoryToolError(errmsg);
            }
        });
    }
}