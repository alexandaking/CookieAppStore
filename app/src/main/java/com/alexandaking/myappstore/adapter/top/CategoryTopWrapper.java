package com.alexandaking.myappstore.adapter.top;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexandaking.myappstore.R;
import com.alexandaking.myappstore.base.StoreApplication;
import com.alexandaking.myappstore.bean.CategoryBean;
import com.alexandaking.myappstore.utils.UIUtils;
import com.bumptech.glide.Glide;
import com.zhxu.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.List;

/**
 * Created by alexandaking on 2017/11/28.
 */

public class CategoryTopWrapper extends HeaderAndFooterWrapper {

    private GridView gridView;
    private Context context;
    private View headerView ;
    private List<CategoryBean.CategoryTopBean> categoryTopBeanList ;

    public CategoryTopWrapper(Context context, RecyclerView.Adapter adapter) {
        super(adapter);
        this.context = context;
        headerView = UIUtils.inflate(R.layout.header_top);
        gridView = (GridView) headerView.findViewById(R.id.gv_title_grid);
        addHeaderView(headerView);
    }

    public void addTopView() {
        addHeaderView(headerView);
    }

    public void deleteTopView() {
        deleteHeaderView(headerView);
    }

    public void adddata(List<CategoryBean.CategoryTopBean> categoryTopBeanList){
        this.categoryTopBeanList = categoryTopBeanList;

        CategorySubAdapter adapter = new CategorySubAdapter(context,categoryTopBeanList);
        gridView.setNumColumns(categoryTopBeanList.size());
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mListener != null){
                    mListener.onItemClick(position);
                }
            }
        });
    }

//    public class GridAdapter extends BaseAdapter{
//
//        private Context mContext;
//        private List<CategoryBean.CategoryTopBean> topBeanList;
//
//        public GridAdapter(Context context, List<CategoryBean.CategoryTopBean> topBeanList){
//            this.mContext =  context;
//            this.topBeanList = topBeanList;
//        }
//
//        @Override
//        public int getCount() {
//            return topBeanList.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return topBeanList.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            CategoryBean.CategoryTopBean categoryTopBean = topBeanList.get(position);
//            ViewHolder holder;
//            if (convertView == null){
//                convertView = UIUtils.inflate(R.layout.appdetail_subcat_title);
//                holder = new ViewHolder();
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolder) convertView.getTag();
//            }
//            holder.appicon = (ImageView) convertView.findViewById(R.id.appicon);
//            holder.itemTitle = (TextView) convertView.findViewById(R.id.ItemTitle);
//
//            holder.itemTitle.setText(categoryTopBean.getName());
//            Glide.with(StoreApplication.getContext()).load(categoryTopBean.getIconUrl()).into(holder.appicon);
//            return convertView;
//        }
//
//        public class ViewHolder{
//            public ImageView appicon;
//            public TextView itemTitle;
//        }
//    }

    private OnItemClickListener  mListener ;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener ;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void clearData() {
        if(categoryTopBeanList != null)
            categoryTopBeanList.clear();
    }
}
