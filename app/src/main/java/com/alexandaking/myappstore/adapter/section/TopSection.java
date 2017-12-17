package com.alexandaking.myappstore.adapter.section;

import android.content.Context;
import android.view.View;

import com.alexandaking.myappstore.R;
import com.alexandaking.myappstore.bean.AppBean;
import com.zhxu.recyclerview.base.ViewHolder;
import com.zhxu.recyclerview.section.StatelessSection;

import java.util.List;

/**
 * Created by alexandaking on 2017/11/30.
 */

public class TopSection extends StatelessSection {

    private Context mContext;
    private String title;
    private List<AppBean> appBeanList;

    public TopSection(Context context, String title, List<AppBean> appBeanList) {
        super(R.layout.applistitem_titlecard, R.layout.applistitem_normal);
        this.mContext = context;
        this.title = title;
        this.appBeanList = appBeanList;
    }

    @Override
    public int getContentItemsTotal() {
        return appBeanList.size();
    }

    @Override
    public ViewHolder getItemViewHolder(View view, int viewType) {
        return new ViewHolder(mContext, view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, int position) {

        AppBean appBean = appBeanList.get(position);
        holder.setText(R.id.appSerial,appBean.getAliasName());
        holder.setImageUrl(R.id.appicon, appBean.getIcon());
        holder.setText(R.id.ItemText,appBean.getName());
        holder.setText(R.id.ItemText_star, appBean.getSizeDesc());
        holder.setText(R.id.memo, appBean.getMemo());
    }

    @Override
    public ViewHolder getHeaderViewHolder(Context context, View view) {
        return new ViewHolder(context, view);
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        super.onBindHeaderViewHolder(holder);
        holder.setText(R.id.ItemTitle, title);
    }
}
