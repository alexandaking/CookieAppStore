package com.alexandaking.myappstore.mvp.interactor;

import com.alexandaking.myappstore.api.CategoryApi;
import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.bean.CategoryBean;
import com.alexandaking.myappstore.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/11/21.
 */

public class CategoryInteractor {

    private IGetDataDelegate<CategoryBean> mDelegate;

    @Inject
    public CategoryInteractor(){

    }

    public void loadCategoryData(BaseActivity baseActivity, IGetDataDelegate<CategoryBean> delegate){

        this.mDelegate = delegate;
        CategoryApi api = new CategoryApi(listener, baseActivity);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(api);

    }

    private HttpOnNextListener<CategoryBean> listener = new HttpOnNextListener<CategoryBean>() {
        @Override
        public void onNext(CategoryBean categoryBean) {
            mDelegate.getDataSuccess(categoryBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            CategoryBean categoryBean = JsonParseUtils.parseCategoryBean(string);
            mDelegate.getDataSuccess(categoryBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
