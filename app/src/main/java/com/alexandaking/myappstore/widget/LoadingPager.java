package com.alexandaking.myappstore.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.alexandaking.myappstore.R;
import com.alexandaking.myappstore.utils.UIUtils;

/**
 * Created by alexandaking on 2017/10/14.
 */

public abstract class LoadingPager extends FrameLayout{

    /**
     * 默认状态
     */
    public final static int STATE_UNKNOW = 0;
    /**
     * 加载中的状态
     */
    public final static int STATE_LOADING = 1;
    /**
     * 加载失败的状态
     */
    public final static int STATE_ERROR = 2;
    /**
     * 加载成功的状态
     */
    public final static int STATE_SUCCESS = 3;
    /**
     * 加载为空的状态
     */
    public final static int STATE_EMPTY = 4;

    private View loadingView, errorView, emptyView, successView;

    private int state = STATE_UNKNOW;


    public LoadingPager(@NonNull Context context) {
        super(context);
        init();
    }

    public LoadingPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingPager(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 将布局填充到帧布局中
     */

    private void init() {
        if(loadingView == null){
            loadingView = createLoadingView();
            this.addView(loadingView,new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
        }
        if(errorView == null){
            errorView = createErrorView();
            this.addView(errorView,new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
        }
        if(emptyView == null){
            emptyView = createEmptyView();
            this.addView(emptyView,new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
        }

        showPager();

    }

    /**
     * 请求网络修改状态
     */
    public void show(){
        if(state == STATE_UNKNOW || state == STATE_ERROR || state == STATE_EMPTY){
            state = STATE_LOADING;
            load();
        }
        showPager();
    }


    /**
     * 设置状态
     * @param result
     */
    public void setState(LoadResult result) {
        state = result.getValue();
        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showPager();
            }
        });
    }

    /**
     * 请求结果的枚举
     */
    public enum LoadResult{
        error(STATE_ERROR),success(STATE_SUCCESS),empty(STATE_EMPTY);
        int value;
        LoadResult(int value){
            this.value=value;
        }
        public int getValue(){
            return value;
        }
    }


    /**
     * 根据不同的状态显示不同的布局
     */
    private void showPager() {
        if(loadingView != null){
            loadingView.setVisibility(state == STATE_UNKNOW || state == STATE_LOADING ? View.VISIBLE : View.GONE);
        }

        if(errorView != null){
            errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE : View.GONE);
        }

        if(emptyView != null){
            emptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE : View.GONE);
        }

        if (state == STATE_SUCCESS && successView == null){
            successView = createSuccessView();
            this.addView(successView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
        }
    }


    private View createLoadingView() {
        return UIUtils.inflate(R.layout.loading_page);
    }
    private View createErrorView() {
        View view = UIUtils.inflate(R.layout.loading_error_page);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        return view;
    }
    private View createEmptyView() {
        return UIUtils.inflate(R.layout.loading_page_empty);
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
