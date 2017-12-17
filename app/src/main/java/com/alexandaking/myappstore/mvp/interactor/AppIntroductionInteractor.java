package com.alexandaking.myappstore.mvp.interactor;

import com.alexandaking.myappstore.api.AppIntroductionApi;
import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.bean.AppIntroductionBean;
import com.alexandaking.myappstore.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/12/9.
 */

public class AppIntroductionInteractor {

    private IGetDataDelegate<AppIntroductionBean> mDelegate;

    @Inject
    public AppIntroductionInteractor(){

    }

    public void loadAppIntroductionData(BaseActivity activity, String packageName,IGetDataDelegate<AppIntroductionBean> delegate){
        this.mDelegate = delegate;
        AppIntroductionApi api = new AppIntroductionApi(listener,activity,packageName);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(api);
    }

    private HttpOnNextListener<AppIntroductionBean> listener = new HttpOnNextListener<AppIntroductionBean>() {
        @Override
        public void onNext(AppIntroductionBean appIntroductionBean) {
            mDelegate.getDataSuccess(appIntroductionBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppIntroductionBean appIntroductionBean = JsonParseUtils.parseAppIntroductionBean(string);
            mDelegate.getDataSuccess(appIntroductionBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
