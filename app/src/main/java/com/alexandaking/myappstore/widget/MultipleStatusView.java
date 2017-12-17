package com.alexandaking.myappstore.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.alexandaking.myappstore.R;
import com.alexandaking.myappstore.utils.UIUtils;
import com.alexandaking.myappstore.utils.ViewUtils;

/**
 * Created by alexandaking on 2017/12/10.
 */

public class MultipleStatusView extends RelativeLayout {


    public static final int STATUS_SUCCESS    = 0;//连接成功，加载成功状态
    public static final int STATUS_LOADING    = 1;//正在加载，加载中状态
    public static final int STATUS_EMPTY      = 2;//加载为空的状态
    public static final int STATUS_ERROR      = 3;//加载结果错误状态

    public int status = STATUS_EMPTY ;

    private View mEmptyView;
    private View mErrorView;
    private View mLoadingView;
    private View mSuccessView;

    private int  mEmptyViewResId;
    private int  mErrorViewResId;
    private int  mLoadingViewResId;
    private int  mSuccessViewResId;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MultipleStatusView(@NonNull Context context) {
        this(context,null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MultipleStatusView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MultipleStatusView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        this(context, attrs, defStyleAttr,-1);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MultipleStatusView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs,defStyleAttr);
        initView();
    }


    public void init(Context context,AttributeSet attrs,int defStyleAttr){
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MultipleStatusView, defStyleAttr, 0);
        mEmptyViewResId = a.getResourceId(R.styleable.MultipleStatusView_emptyView, R.layout.empty_view);
        mErrorViewResId = a.getResourceId(R.styleable.MultipleStatusView_errorView, R.layout.loading_error_page);
        mLoadingViewResId = a.getResourceId(R.styleable.MultipleStatusView_loadingView, R.layout.loading_page);
        mSuccessViewResId = a.getResourceId(R.styleable.MultipleStatusView_successView, -1);
        a.recycle();
    }


    public void initView(){
        if(mEmptyView == null) {
            mEmptyView = UIUtils.inflate(mEmptyViewResId);
            addView(mEmptyView,new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
            mEmptyView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(loadListener != null){
                        loadListener.loading();
                    }
                }
            });
        }
        if(mErrorView == null) {
            mErrorView = UIUtils.inflate(mErrorViewResId);
            addView(mErrorView,new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

            mErrorView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(loadListener != null){
                        loadListener.loading();
                    }
                }
            });
        }
        if(mLoadingView == null) {
            mLoadingView = UIUtils.inflate(mLoadingViewResId);
            addView(mLoadingView,new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        }
        showStateView();


    }

    public LoadListener loadListener ;

    public void setLoadListener(LoadListener loadListener){
        this.loadListener = loadListener ;
        showStateView();
    }

    public interface LoadListener {
        void loading() ;
        void loadView() ;
    }



    public void showStateView(){
        if(status == STATUS_ERROR || status == STATUS_EMPTY){
            status = STATUS_LOADING ;
        }
        if(status == STATUS_LOADING){
            if(loadListener != null){
                loadListener.loading();
            }
        }
        showView();

    }


    public void showView(){
        if(mLoadingView != null){
            mLoadingView.setVisibility(status == STATUS_LOADING? View.VISIBLE : View.GONE);
        }
        if(mErrorView != null){

            mErrorView.setVisibility(status == STATUS_ERROR ? View.VISIBLE : View.GONE);

        }
        if(mEmptyView != null){

            mEmptyView.setVisibility(status == STATUS_EMPTY ? View.VISIBLE : View.GONE);

        }
        if(status == STATUS_SUCCESS && mSuccessView != null){
            //如果mSuccessView有父控件了，则将其父控件移除
            ViewUtils.removeSelfFromParent(mSuccessView);
            addView(mSuccessView,new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
            if(loadListener != null){
                loadListener.loadView();
            }
        }

        if(mSuccessView != null){
            mSuccessView.setVisibility(status == STATUS_SUCCESS ? View.VISIBLE : View.GONE);
        }
    }


    /** 服务器返回状态枚举 */
    public enum LoadResult {
        error(STATUS_ERROR),empty(STATUS_EMPTY),success(STATUS_SUCCESS);
        int value ;
        LoadResult(int value){
            this.value = value ;
        }

        public int getValue(){
            return value ;
        }
    }
    public void setState(LoadResult result){
        status = result.getValue() ;
        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showView();
            }
        });
    }

    public View getSuccessView() {
        return mSuccessView;
    }

    public void setSuccessView(View mSuccessView) {
        this.mSuccessView = mSuccessView;
    }
}
