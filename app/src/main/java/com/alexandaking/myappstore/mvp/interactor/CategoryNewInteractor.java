package com.alexandaking.myappstore.mvp.interactor;

import com.alexandaking.myappstore.api.CategoryNewApi;
import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.bean.CategoryNewBean;
import com.alexandaking.myappstore.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/12/13.
 */

public class CategoryNewInteractor {

    private final ThreadLocal<IGetDataDelegate<CategoryNewBean>> mDelegate = new ThreadLocal<>();

    @Inject
    public CategoryNewInteractor(){

    }

    public void loadCategoryNewData(BaseActivity activity, IGetDataDelegate<CategoryNewBean> delegate){
        this.mDelegate.set(delegate);
        CategoryNewApi categoryNewApi = new CategoryNewApi(httpListener,activity);
        HttpManager httpListener = HttpManager.getInstance() ;
        httpListener.doHttpDeal(categoryNewApi);
    }

    private HttpOnNextListener<CategoryNewBean> httpListener = new HttpOnNextListener<CategoryNewBean>() {
        @Override
        public void onNext(CategoryNewBean categoryNewBean) {
            mDelegate.get().getDataSuccess(categoryNewBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            CategoryNewBean categoryNewBean = JsonParseUtils.parseCategoryNewBean(string);
            mDelegate.get().getDataSuccess(categoryNewBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.get().getDataError(e.getMessage());
        }
    };
}