package com.alexandaking.myappstore.mvp.interactor;

import com.alexandaking.myappstore.api.CategoryToolApi;
import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.bean.CategoryToolBean;
import com.alexandaking.myappstore.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/12/13.
 */

public class CategoryToolActivityInteractor {

    private IGetDataDelegate<CategoryToolBean> mDelegate ;

    @Inject
    public CategoryToolActivityInteractor(){

    }

    public void loadCategoryToolData(BaseActivity activity, IGetDataDelegate<CategoryToolBean> delegate){
        this.mDelegate = delegate ;

        CategoryToolApi categoryToolApi = new CategoryToolApi(httpListener,activity);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(categoryToolApi);
    }

    private HttpOnNextListener httpListener = new HttpOnNextListener<CategoryToolBean>() {
        @Override
        public void onNext(CategoryToolBean categoryToolBean) {
            mDelegate.getDataSuccess(categoryToolBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            CategoryToolBean categoryToolBean = JsonParseUtils.parseCategoryToolBean(string);
            mDelegate.getDataSuccess(categoryToolBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };

}
