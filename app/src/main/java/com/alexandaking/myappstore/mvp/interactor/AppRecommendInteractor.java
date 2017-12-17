package com.alexandaking.myappstore.mvp.interactor;

import com.alexandaking.myappstore.api.AppRecommendApi;
import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.bean.AppRecommendBean;
import com.alexandaking.myappstore.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/12/12.
 */

public class AppRecommendInteractor {

    private IGetDataDelegate<AppRecommendBean> mDelegate;

    @Inject
    public AppRecommendInteractor(){

    }

    public void loadAppRecommend(BaseActivity activity, String packageName, IGetDataDelegate<AppRecommendBean> delegate){
        this.mDelegate = delegate;
        AppRecommendApi api = new AppRecommendApi(httplistener, activity, packageName);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(api);
    }

    private HttpOnNextListener<AppRecommendBean> httplistener = new HttpOnNextListener<AppRecommendBean>() {
        @Override
        public void onNext(AppRecommendBean appRecommendBean) {
            mDelegate.getDataSuccess(appRecommendBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppRecommendBean appRecommendBean = JsonParseUtils.parseAppRecommendBean(string);
            mDelegate.getDataSuccess(appRecommendBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
