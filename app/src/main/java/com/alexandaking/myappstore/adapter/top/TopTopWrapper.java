package com.alexandaking.myappstore.adapter.top;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexandaking.myappstore.R;
import com.alexandaking.myappstore.base.StoreApplication;
import com.alexandaking.myappstore.bean.CategoryBean;
import com.alexandaking.myappstore.bean.TopBean;
import com.alexandaking.myappstore.utils.UIUtils;
import com.bumptech.glide.Glide;
import com.zhxu.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.List;

/**
 * Created by alexandaking on 2017/11/28.
 */

public class TopTopWrapper extends HeaderAndFooterWrapper {

    private GridView gridView;
    private Context context;
    private List<CategoryBean.CategoryTopBean> categoryTopBeanList ;

    public TopTopWrapper(Context context, RecyclerView.Adapter adapter) {
        super(adapter);
        this.context = context;
        View view = UIUtils.inflate(R.layout.header_top);
        gridView = (GridView) view.findViewById(R.id.gv_title_grid);
        addHeaderView(view);
    }

    public void adddata(List<TopBean.TopTopBean> topTopBeans){
        gridView.setNumColumns(topTopBeans.size());
        GridAdapter adapter = new GridAdapter(context,topTopBeans);
        gridView.setAdapter(adapter);
    }

    public class GridAdapter extends BaseAdapter{

        private Context mContext;
        private List<TopBean.TopTopBean> topTopBeanList;

        public GridAdapter(Context context, List<TopBean.TopTopBean> topBeanList){
            this.mContext =  context;
            this.topTopBeanList = topBeanList;
        }

        @Override
        public int getCount() {
            return topTopBeanList.size();
        }

        @Override
        public Object getItem(int position) {
            return topTopBeanList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TopBean.TopTopBean topTopBean = topTopBeanList.get(position);
            ViewHolder holder;
            if (convertView == null){
                convertView = UIUtils.inflate(R.layout.appdetail_subcat_title);
                holder = new ViewHolder();
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.appicon = (ImageView) convertView.findViewById(R.id.appicon);
            holder.itemTitle = (TextView) convertView.findViewById(R.id.ItemTitle);

            holder.itemTitle.setText(topTopBean.getName());
            Glide.with(StoreApplication.getContext()).load(topTopBean.getIconUrl()).into(holder.appicon);
            return convertView;
        }

        public class ViewHolder{
            public ImageView appicon;
            public TextView itemTitle;
        }
    }
}
