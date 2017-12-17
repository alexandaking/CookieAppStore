package com.alexandaking.myappstore.mvp.interactor;

import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.api.TopApi;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.bean.TopBean;
import com.alexandaking.myappstore.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/11/22.
 */

public class TopInteractor {

    private IGetDataDelegate<TopBean> mDelegate;

    @Inject
    public TopInteractor(){

    }

    public void loadTopData(BaseActivity activity, IGetDataDelegate<TopBean> delegate){
        this.mDelegate = delegate;
        TopApi api = new TopApi(listener, activity);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(api);
    }

    private HttpOnNextListener<TopBean> listener = new HttpOnNextListener<TopBean>() {
        @Override
        public void onNext(TopBean topBean) {
            mDelegate.getDataSuccess(topBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            TopBean topBean = JsonParseUtils.parseTopBean(string);
            mDelegate.getDataSuccess(topBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };

}
