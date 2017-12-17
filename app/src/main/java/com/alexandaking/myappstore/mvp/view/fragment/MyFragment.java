package com.alexandaking.myappstore.mvp.view.fragment;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexandaking.myappstore.R;
import com.alexandaking.myappstore.base.BaseFragment;
import com.alexandaking.myappstore.base.StoreApplication;
import com.alexandaking.myappstore.bean.MyGvBean;
import com.alexandaking.myappstore.utils.UIUtils;
import com.alexandaking.myappstore.widget.LoadingPager;
import com.alexandaking.myappstore.widget.MyEnterLayout;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandaking on 2017/10/9.
 */

public class MyFragment extends BaseFragment{

    @BindView(R.id.gv_title_grid)
    GridView gv_title_grid ;
    @BindView(R.id.book_game_layout)
    MyEnterLayout book_game_layout ;
    @BindView(R.id.buy_layout)
    MyEnterLayout buy_layout ;
    @BindView(R.id.huaban_layout)
    MyEnterLayout huaban_layout ;
    @BindView(R.id.setting_computer)
    MyEnterLayout setting_computer ;
    @BindView(R.id.faq_layout)
    MyEnterLayout faq_layout ;
    @BindView(R.id.check_update_layout)
    MyEnterLayout check_update_layout ;
    @BindView(R.id.about_layout)
    MyEnterLayout about_layout ;

    private List<MyGvBean> gvBeanList = new ArrayList<>() ;

    @Override
    protected void load() {
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    protected View createSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_my);
        ButterKnife.bind(this, view);

        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.market_prize),R.drawable.icon_market_lucky_draw));
        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.market_gift),R.drawable.ic_mine_package_normal));
        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.appzone_comments),R.drawable.icon_market_comment));
        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.market_mine_message),R.drawable.icon_market_message));

        gv_title_grid.setNumColumns(gvBeanList.size());
        GvAdapter adapter = new GvAdapter(getContext(), gvBeanList);
        gv_title_grid.setAdapter(adapter);

        book_game_layout.setTitle(UIUtils.getString(R.string.reserve_warpup_game_str));
        buy_layout.setTitle(UIUtils.getString(R.string.purchase_title));
        huaban_layout.setTitle(UIUtils.getString(R.string.mine_point_area));
        setting_computer.setTitle(UIUtils.getString(R.string.action_settings));
        faq_layout.setTitle(UIUtils.getString(R.string.menu_feedback));
        check_update_layout.setTitle(UIUtils.getString(R.string.settings_check_version_update));
        about_layout.setTitle(UIUtils.getString(R.string.about));


        return view;
    }

    public class GvAdapter extends BaseAdapter{

        private Context context;
        private List<MyGvBean> gvBeanList;

        public GvAdapter(Context context, List<MyGvBean> gvBeanList){
            this.context = context;
            this.gvBeanList = gvBeanList;
        }

        @Override
        public int getCount() {
            return gvBeanList.size();
        }

        @Override
        public Object getItem(int position) {
            return gvBeanList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            MyGvBean myGvBean = gvBeanList.get(position);
            ViewHolder holder;
            if(convertView == null){
                convertView = UIUtils.inflate(R.layout.appdetail_subcat_title);
                holder = new ViewHolder();
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.appicon = (ImageView) convertView.findViewById(R.id.appicon);
            holder.itemtitle = (TextView) convertView.findViewById(R.id.ItemTitle);

            holder.itemtitle.setText(myGvBean.getName());
            Glide.with(StoreApplication.getContext()).load(myGvBean.getIconId()).into(holder.appicon);
            return convertView;
        }

        class ViewHolder{
            public ImageView appicon;
            public TextView itemtitle;
        }
    }
}
