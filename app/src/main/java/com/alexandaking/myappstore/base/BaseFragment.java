package com.alexandaking.myappstore.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexandaking.myappstore.base.mvpbase.BaseView;
import com.alexandaking.myappstore.widget.LoadingPager;

/**
 * Created by alexandaking on 2017/10/9.
 */

public abstract class BaseFragment extends Fragment implements BaseView{

    private LoadingPager loadingPager;
    protected BaseActivity mActivity;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(loadingPager == null){
            loadingPager = new LoadingPager(getContext()) {
                @Override
                protected View createSuccessView() {
                    return BaseFragment.this.createSuccessView();
                }

                @Override
                protected void load() {
                    BaseFragment.this.load();
                }
            };
        }
        return loadingPager;
    }

    public void show(){
        if(loadingPager != null){
            loadingPager.show();
        }
    }

    public void setState(LoadingPager.LoadResult result){
        if (loadingPager != null){
            loadingPager.setState(result);
        }
    }

    @Override
    public void showToast(String msg) {
        mActivity.showToast(msg);
    }

    /**
     * 加载成功界面
     * @return
     */
    protected abstract View createSuccessView();

    /**
     * 请求网络修改状态
     */
    protected abstract void load();
}
