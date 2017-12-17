package com.alexandaking.myappstore.mvp.interactor;

import com.alexandaking.myappstore.api.AppMoreRecommendApi;
import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.bean.AppMoreRecommendBean;
import com.alexandaking.myappstore.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/12/13.
 */

public class AppMoreRecommendInteractor {

    private IGetDataDelegate<AppMoreRecommendBean> mDelegate;

    @Inject
    public AppMoreRecommendInteractor (){}

    public void loadAppMoreRecommend(BaseActivity activity, String type, String packageName, IGetDataDelegate<AppMoreRecommendBean> delegate){
        this.mDelegate = delegate ;
        AppMoreRecommendApi api = new AppMoreRecommendApi(httpListener,activity,type,packageName);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(api);
    }

    private HttpOnNextListener<AppMoreRecommendBean> httpListener = new HttpOnNextListener<AppMoreRecommendBean>() {
        @Override
        public void onNext(AppMoreRecommendBean appMoreRecommendBean) {
            mDelegate.getDataSuccess(appMoreRecommendBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppMoreRecommendBean appMoreRecommendBean = JsonParseUtils.parseAppMoreRecommendBean(string);
            mDelegate.getDataSuccess(appMoreRecommendBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
