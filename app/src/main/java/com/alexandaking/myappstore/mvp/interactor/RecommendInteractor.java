package com.alexandaking.myappstore.mvp.interactor;

import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.api.RecommendApi;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.bean.RecommendBean;
import com.alexandaking.myappstore.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/11/20.
 */

public class RecommendInteractor {

    private IGetDataDelegate<RecommendBean> mDelegate;

    @Inject
    public RecommendInteractor(){

    }


    /**
     * 执行网络请求
     * 从网络获取数据
     */
    public void loadRecommendData(BaseActivity activity, IGetDataDelegate<RecommendBean> delegate){
        this.mDelegate = delegate;
        RecommendApi api = new RecommendApi(listener, activity);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(api);
    }

    private HttpOnNextListener<RecommendBean> listener = new HttpOnNextListener<RecommendBean>() {
        @Override
        public void onNext(RecommendBean recommendBean) {
            mDelegate.getDataSuccess(recommendBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            RecommendBean recommendBean = JsonParseUtils.parseRecommendBean(string);
            mDelegate.getDataSuccess(recommendBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
