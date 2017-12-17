package com.alexandaking.myappstore.mvp.interactor;

import com.alexandaking.myappstore.api.AppCommentApi;
import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.bean.AppCommentBean;
import com.alexandaking.myappstore.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/12/12.
 */

public class AppCommentInteractor {

    private IGetDataDelegate<AppCommentBean> mDelegate;

    @Inject
    public AppCommentInteractor(){

    }

    public void loadAppCommentData(BaseActivity activity, String packageName, IGetDataDelegate<AppCommentBean> delegate){
        this.mDelegate = delegate;
        AppCommentApi api = new AppCommentApi(httplistener, activity, packageName);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(api);
    }


    private HttpOnNextListener<AppCommentBean> httplistener = new HttpOnNextListener<AppCommentBean>() {
        @Override
        public void onNext(AppCommentBean appCommentBean) {
           mDelegate.getDataSuccess(appCommentBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppCommentBean appCommentBean = JsonParseUtils.parseAppCommentBean(string);
            mDelegate.getDataSuccess(appCommentBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
