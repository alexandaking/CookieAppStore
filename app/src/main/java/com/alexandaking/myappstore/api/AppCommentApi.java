package com.alexandaking.myappstore.api;

import com.alexandaking.myappstore.bean.AppCommentBean;
import com.alexandaking.myappstore.utils.JsonParseUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhxu.library.api.BaseApi;
import com.zhxu.library.listener.HttpOnNextListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by alexandaking on 2017/12/12.
 */

public class AppCommentApi extends BaseApi<AppCommentBean>{

    private String packageName;

    public AppCommentApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String packageName) {
        super(listener, rxAppCompatActivity);
        //setMothed("AppStore/app/comment/"+packageName);
        this.packageName = packageName;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getAppCommentData(packageName);
    }

    @Override
    public AppCommentBean call(ResponseBody responseBody) {
        String result = "";
        try {
            result = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("result:"+result);
        return JsonParseUtils.parseAppCommentBean(result);
    }
}
