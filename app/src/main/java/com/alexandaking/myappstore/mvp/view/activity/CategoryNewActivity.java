package com.alexandaking.myappstore.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alexandaking.myappstore.R;
import com.alexandaking.myappstore.adapter.top.CategoryNewTopWrapper;
import com.alexandaking.myappstore.base.BaseMvpActivity;
import com.alexandaking.myappstore.bean.AppBean;
import com.alexandaking.myappstore.bean.CategoryNewBean;
import com.alexandaking.myappstore.mvp.presenter.impl.CategoryNewPresenterImpl;
import com.alexandaking.myappstore.mvp.view.view.CategoryNewView;
import com.zhxu.recyclerview.adapter.CommonAdapter;
import com.zhxu.recyclerview.adapter.MultiItemTypeAdapter;
import com.zhxu.recyclerview.base.ViewHolder;

import javax.inject.Inject;

import butterknife.BindView;

public class CategoryNewActivity extends BaseMvpActivity<CategoryNewPresenterImpl> implements CategoryNewView {

    @BindView(R.id.title_text)
    TextView title_text ;
    //    @BindView(R.id.iv_search)
//    ImageView iv_search ;
    @BindView(R.id.rv)
    RecyclerView rv ;

    @Inject
    CategoryNewPresenterImpl categoryNewPresenter ;


    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_category_new);
    }

    @Override
    protected void initView() {
        //设置沉浸式状态栏
        setStatusBar();
//        iv_search.setVisibility(View.VISIBLE);
        //设置沉浸式状态栏背景
        bar_layout.setBackgroundResource(R.color.black_alpha_5);
        title_text.setText("今日首发");
    }

    @Override
    protected void initData() {
        super.initData();
        categoryNewPresenter.getCategoryNewData(this);
    }

    @Override
    protected CategoryNewPresenterImpl initInjector() {
        mActivityComponent.inject(this);
        return categoryNewPresenter;
    }

    @Override
    public void onCategoryNewDataSuccess(final CategoryNewBean categoryNewBean) {
        rv.setLayoutManager(new LinearLayoutManager(this));
        CategoryNewAdapter adapter = new CategoryNewAdapter(this);
        adapter.addDataAll(categoryNewBean.getAppBeanList());
        CategoryNewTopWrapper categoryNewTopWrapper = new CategoryNewTopWrapper(this,adapter,categoryNewBean.getHead());
        rv.setAdapter(categoryNewTopWrapper);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                AppBean appBean = categoryNewBean.getAppBeanList().get(position);
                Intent intent = new Intent(CategoryNewActivity.this,AppDetailActivity.class);
                intent.putExtra("packageName",appBean.getPackageName());
                startActivity(intent);

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                return false;
            }
        });
    }

    @Override
    public void onCategoryNewDataError(String msg) {

    }

    class CategoryNewAdapter extends CommonAdapter<AppBean> {

        public CategoryNewAdapter(Context context) {
            super(context, R.layout.applistitem_recommend);
        }
        @Override
        protected void convert(ViewHolder holder, AppBean appBean, int position) {
            holder.setText(R.id.appTitle,appBean.getName());
            holder.setText(R.id.app_size,appBean.getSizeDesc());
            holder.setText(R.id.app_des,appBean.getMemo());
            holder.setImageUrl(R.id.appicon,appBean.getIcon());
        }
    }
}
