package com.alexandaking.myappstore.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.alexandaking.myappstore.base.mvpbase.BasePresenter;
import com.alexandaking.myappstore.base.mvpbase.BaseView;
import com.alexandaking.myappstore.di.component.DaggerFragmentComponent;
import com.alexandaking.myappstore.di.component.FragmentComponent;
import com.alexandaking.myappstore.di.module.FragmentModule;

/**
 * Created by alexandaking on 2017/10/25.
 */

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView{

    protected FragmentComponent mFragmentComponent;
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragmentComponent();
        mPresenter = initInjector();
        mPresenter.attachView(this);
    }

    public void initFragmentComponent(){
        mFragmentComponent = DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(this))
                .appComponent(((StoreApplication)getActivity().getApplication()).getApplicationComponent())
                .build();
    }

    /**
     * 完后一览注入并返回注入的Presenter
     * @return
     */
    protected  abstract T initInjector();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detachView();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
    }
}
