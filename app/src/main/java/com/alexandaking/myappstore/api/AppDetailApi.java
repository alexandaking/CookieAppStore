package com.alexandaking.myappstore.api;

import android.util.Log;

import com.alexandaking.myappstore.bean.AppDetailBean;
import com.alexandaking.myappstore.utils.JsonParseUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhxu.library.api.BaseApi;
import com.zhxu.library.listener.HttpOnNextListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by alexandaking on 2017/12/4.
 */

public class AppDetailApi extends BaseApi<AppDetailBean> {

    private String packageName;

    public AppDetailApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String packageName) {
        super(listener, rxAppCompatActivity);
        this.packageName = packageName;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getAppDetailData(packageName);
    }

    @Override
    public AppDetailBean call(ResponseBody responseBody) {
        String result = "";
        try {
            result = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonParseUtils.parseAppDetailBean(result);
    }
}
