package com.alexandaking.myappstore.api;

import com.alexandaking.myappstore.bean.CategoryNewBean;
import com.alexandaking.myappstore.utils.JsonParseUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhxu.library.api.BaseApi;
import com.zhxu.library.listener.HttpOnNextListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by alexandaking on 2017/12/13.
 */

public class CategoryNewApi extends BaseApi<CategoryNewBean> {
    public CategoryNewApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setMothed("AppStore/categorydata/new");
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getCategoryNewData();
    }

    @Override
    public CategoryNewBean call(ResponseBody responseBody) {
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JsonParseUtils.parseCategoryNewBean(string);
    }
}