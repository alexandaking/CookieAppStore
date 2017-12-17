package com.alexandaking.myappstore.api;


import com.alexandaking.myappstore.bean.CategoryBean;
import com.alexandaking.myappstore.utils.JsonParseUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhxu.library.api.BaseApi;
import com.zhxu.library.listener.HttpOnNextListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by alexandaking on 2017/11/21.
 */

public class CategoryApi extends BaseApi<CategoryBean> {

    public CategoryApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getCategoryData();
    }

    @Override
    public CategoryBean call(ResponseBody responseBody) {
        //通过map的转换规则
        String result = "";

        try {
            result = responseBody.string();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return JsonParseUtils.parseCategoryBean(result);
    }
}
