package com.alexandaking.myappstore.mvp.interactor;

import com.alexandaking.myappstore.api.CategoryNecessaryApi;
import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.bean.CategoryNecessaryBean;
import com.alexandaking.myappstore.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/12/13.
 */

public class CategoryNecessaryInteractor {

    private IGetDataDelegate<CategoryNecessaryBean> mDelegate ;

    @Inject
    public CategoryNecessaryInteractor(){

    }

    public void loadCategoryNecessaryData(BaseActivity activity, IGetDataDelegate<CategoryNecessaryBean> delegate){
        this.mDelegate = delegate ;
        CategoryNecessaryApi categoryNecessaryApi = new CategoryNecessaryApi(httpListener,activity);
        HttpManager httpListener = HttpManager.getInstance();
        httpListener.doHttpDeal(categoryNecessaryApi);
    }

    private HttpOnNextListener<CategoryNecessaryBean> httpListener = new HttpOnNextListener<CategoryNecessaryBean>() {
        @Override
        public void onNext(CategoryNecessaryBean categoryNecessaryBean) {
            mDelegate.getDataSuccess(categoryNecessaryBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            CategoryNecessaryBean categoryNecessaryBean = JsonParseUtils.parseCategoryNecessaryBean(string);
            mDelegate.getDataSuccess(categoryNecessaryBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
