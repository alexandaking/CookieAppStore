package com.alexandaking.myappstore.api;

/**
 *
 * 网络请求回调
 *
 * Created by alexandaking on 2017/11/17.
 */

public interface IGetDataDelegate<T> {
    void getDataSuccess(T t);
    void getDataError(String errmsg);
}