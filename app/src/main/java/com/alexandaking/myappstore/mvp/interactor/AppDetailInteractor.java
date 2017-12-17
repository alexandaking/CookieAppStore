package com.alexandaking.myappstore.mvp.interactor;

import android.util.Log;

import com.alexandaking.myappstore.api.AppDetailApi;
import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.bean.AppDetailBean;
import com.alexandaking.myappstore.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/12/4.
 */

public class AppDetailInteractor {

    private IGetDataDelegate<AppDetailBean> mDelegate ;

    @Inject
    public AppDetailInteractor(){

    }

    public void loadAppDetailData(BaseActivity activity,String packageName,IGetDataDelegate<AppDetailBean> delegate){
        this.mDelegate = delegate ;
        AppDetailApi appDetailApi = new AppDetailApi(httpListener,activity,packageName);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(appDetailApi);
    }

    private HttpOnNextListener<AppDetailBean> httpListener = new HttpOnNextListener<AppDetailBean>() {
        @Override
        public void onNext(AppDetailBean appDetailBean) {
            mDelegate.getDataSuccess(appDetailBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppDetailBean appDetailBean = JsonParseUtils.parseAppDetailBean(string);
            mDelegate.getDataSuccess(appDetailBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };

}
