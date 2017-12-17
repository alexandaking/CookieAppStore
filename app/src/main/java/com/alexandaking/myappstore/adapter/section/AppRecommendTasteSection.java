package com.alexandaking.myappstore.adapter.section;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexandaking.myappstore.R;
import com.alexandaking.myappstore.bean.AppBean;
import com.alexandaking.myappstore.mvp.view.activity.AppDetailActivity;
import com.alexandaking.myappstore.mvp.view.activity.AppMoreRecommendActivity;
import com.alexandaking.myappstore.widget.DownloadProgressButton;
import com.zhxu.recyclerview.base.ViewHolder;
import com.zhxu.recyclerview.section.StatelessSection;

import java.util.List;

/**
 * Created by alexandaking on 2017/12/13.
 */

public class AppRecommendTasteSection extends StatelessSection {
    private Context mContext ;
    private String title ;
    private List<AppBean> tasteAppBeanList ;
    private String packageName ;

    public AppRecommendTasteSection(Context context, String title, List<AppBean> tasteAppBeanList,String packageName) {
        super(R.layout.appdetail_recommend_list_title,R.layout.applistitem_recommend);
        this.mContext = context ;
        this.title = title ;
        this.tasteAppBeanList = tasteAppBeanList ;
        this.packageName = packageName ;
    }

    @Override
    public int getContentItemsTotal() {
        return tasteAppBeanList.size();
    }

    @Override
    public ViewHolder getItemViewHolder(View view, int viewType) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, int position) {
        final AppBean tasteAppBean = tasteAppBeanList.get(position);
        holder.setImageUrl(R.id.appicon,tasteAppBean.getIcon());
        holder.setText(R.id.appTitle,tasteAppBean.getName());
        holder.setText(R.id.app_size,tasteAppBean.getIntro());
        holder.setText(R.id.app_des,tasteAppBean.getMemo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AppDetailActivity.class);
                intent.putExtra("packageName",tasteAppBean.getPackageName());
                ((AppDetailActivity)mContext).startActivity(intent);
            }
        });
    }

    @Override
    public ViewHolder getHeaderViewHolder(Context context, View view) {
        return new TitleViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        holder.setText(R.id.titleText,title);
        holder.setOnClickListener(R.id.more_btn, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,AppMoreRecommendActivity.class);
                intent.putExtra("type","taste");
                intent.putExtra("packageName",packageName);
                ((AppDetailActivity)mContext).startActivity(intent);
            }
        });
    }

    public class TitleViewHolder extends ViewHolder{
        TextView tvTitle;
        TextView more_btn ;

        public TitleViewHolder(View view) {
            super(mContext,view);
            tvTitle = (TextView) view.findViewById(R.id.titleText);
            more_btn = (TextView) view.findViewById(R.id.more_btn);
        }
    }

    public class ItemViewHolder extends ViewHolder{
        ImageView appicon ;
        DownloadProgressButton downbtn ;
        TextView appTitle ;
        TextView app_size ;
        TextView app_des ;

        public ItemViewHolder(View itemView) {
            super(mContext,itemView);
            appicon = (ImageView) itemView.findViewById(R.id.appicon);
            downbtn = (DownloadProgressButton) itemView.findViewById(R.id.downbtn);
            appTitle = (TextView) itemView.findViewById(R.id.appTitle);
            app_size = (TextView) itemView.findViewById(R.id.app_size);
            app_des = (TextView) itemView.findViewById(R.id.app_des);
        }
    }
}