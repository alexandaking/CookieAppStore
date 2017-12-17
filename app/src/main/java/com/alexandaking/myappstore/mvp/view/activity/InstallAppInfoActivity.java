package com.alexandaking.myappstore.mvp.view.activity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alexandaking.myappstore.R;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.bean.AppInfo;
import com.alexandaking.myappstore.utils.AppInfoUtils;
import com.alexandaking.myappstore.utils.DateUtils;
import com.zhxu.recyclerview.adapter.CommonAdapter;
import com.zhxu.recyclerview.adapter.MultiItemTypeAdapter;
import com.zhxu.recyclerview.base.ViewHolder;

import java.util.List;

import butterknife.BindView;

public class InstallAppInfoActivity extends BaseActivity {

    @BindView(R.id.title_text)
    TextView title_text ;
    @BindView(R.id.rv)
    RecyclerView rv ;


    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_install_app_info);
    }

    @Override
    protected void initView() {
        //设置沉浸式状态栏
        setStatusBar();
        //设置沉浸式状态栏背景
        bar_layout.setBackgroundResource(R.color.black_alpha_5);
        title_text.setText("安装管理");

        final List<AppInfo> appInfos = AppInfoUtils.getAppInfos(this);

        final AppInfoAdapter adapter = new AppInfoAdapter(this) ;
        adapter.addDataAll(appInfos);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                AppInfoUtils.openApplication(InstallAppInfoActivity.this,appInfos.get(position).getPackageName());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                return false;
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    class AppInfoAdapter extends CommonAdapter<AppInfo> {


        public AppInfoAdapter(Context context) {
            super(context, R.layout.install_record_item);
        }


        @Override
        protected void convert(final ViewHolder holder, final AppInfo appInfo, int position) {
            holder.setImageDrawable(R.id.localpackage_item_icon_view,appInfo.getIcon());
            holder.setText(R.id.localpackage_item_name_view,appInfo.getName());
            holder.setText(R.id.localpackage_item_version_view,appInfo.getVersionName());
            holder.setText(R.id.localpackage_item_date_view, DateUtils.getDate(appInfo.getFirstInstallTime()));

            holder.setOnClickListener(R.id.ll_info, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LinearLayout ll = (LinearLayout) holder.itemView.findViewById(R.id.uninstall_list_actions_layout);
                    ll.setVisibility(ll.isShown() ? View.GONE : View.VISIBLE);
                }
            });

            holder.setOnClickListener(R.id.localpackage_option_button, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //卸载应用
                    AppInfoUtils.uninstallApplication(InstallAppInfoActivity.this,appInfo.getPackageName());
                }
            });
            holder.setOnClickListener(R.id.app_management_button, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //应用详情
                    AppInfoUtils.showInstalledAppDetails(InstallAppInfoActivity.this,appInfo.getPackageName());
                }
            });
        }
    }
}
