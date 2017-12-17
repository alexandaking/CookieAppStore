package com.alexandaking.myappstore.mvp.view.fragment;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alexandaking.myappstore.R;
import com.alexandaking.myappstore.base.BaseMvpFragment;
import com.alexandaking.myappstore.bean.AppIntroductionBean;
import com.alexandaking.myappstore.mvp.presenter.impl.AppIntroductionPresenterImpl;
import com.alexandaking.myappstore.mvp.view.activity.AppDetailActivity;
import com.alexandaking.myappstore.mvp.view.activity.GalleryDetailActivity;
import com.alexandaking.myappstore.mvp.view.view.AppIntroductionFragmentView;
import com.alexandaking.myappstore.utils.UIUtils;
import com.alexandaking.myappstore.widget.FlowLayout;
import com.alexandaking.myappstore.widget.FoldingTextView;
import com.alexandaking.myappstore.widget.LoadingPager;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandaking on 2017/12/6.
 */

public class AppIntroductionFragment extends BaseMvpFragment<AppIntroductionPresenterImpl> implements AppIntroductionFragmentView, View.OnClickListener {

    @BindView(R.id.app_detail_gallery_container_linearlayout)
    LinearLayout app_detail_gallery_container ;
    @BindView(R.id.detail_appinfo_tariff_textview)
    TextView appInfoTariff ;
    @BindView(R.id.detail_appinfo_size_textview)
    TextView appInfoSize ;
    @BindView(R.id.detail_appinfo_version_textview)
    TextView appInfoVersion ;
    @BindView(R.id.detail_appinfo_release_date_textview)
    TextView appInfoDate ;
    @BindView(R.id.appdetail_appinfo_developer_textview)
    TextView appInfoDeveloper ;
    @BindView(R.id.ll)
    LinearLayout appInfoDes ;
    @BindView(R.id.flowLayout)
    FlowLayout flowLayout ;

    @Inject
    public AppIntroductionPresenterImpl appIntroductionPresenter;

    private String packageName;
    private AppIntroductionBean appIntroductionBean;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        packageName = ((AppDetailActivity)getActivity()).getDetailPackageName();
        show();
    }

    @Override
    public void onAppIntroductionDataSuccess(AppIntroductionBean appIntroductionBean) {
        this.appIntroductionBean = appIntroductionBean;
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void onAppIntroductionDataError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected AppIntroductionPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return appIntroductionPresenter;
    }

    @Override
    protected View createSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_app_introduction);
        ButterKnife.bind(this,view);

        for(int i = 0 ;i < appIntroductionBean.getImageCompressList().size() ; i ++){
            String url = appIntroductionBean.getImageCompressList().get(i);
            View screenView = View.inflate(getContext(),R.layout.appdetail_item_screen_image,null) ;
            ImageView screenImageView = (ImageView) screenView.findViewById(R.id.appdetail_screen_img_imageview);
            screenImageView.setContentDescription(screenImageView.getResources().getString(R.string.appdetail_screenshot));
            screenImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            screenView.setOnClickListener(this);
            screenView.setTag(i);
            Glide.with(UIUtils.getContext()).load(url).into(screenImageView);
            app_detail_gallery_container.addView(screenView);
        }

        appInfoTariff.setText(appIntroductionBean.getAppInfoBean().getTariffDesc());
        appInfoSize.setText(Formatter.formatFileSize(getContext(),Long.parseLong(appIntroductionBean.getAppInfoBean().getSize())));
        appInfoDate.setText(appIntroductionBean.getAppInfoBean().getReleaseDate());
        appInfoVersion.setText(appIntroductionBean.getAppInfoBean().getVersion());
        appInfoDeveloper.setText(appIntroductionBean.getAppInfoBean().getDeveloper());

        for(int i = 0 ; i < appIntroductionBean.getAppDetailInfoBeanList().size() ; i ++){
            FoldingTextView foldingTextView = new FoldingTextView(getContext()) ;
            foldingTextView.setTitle(appIntroductionBean.getAppDetailInfoBeanList().get(i).getTitle());
            foldingTextView.setContent(appIntroductionBean.getAppDetailInfoBeanList().get(i).getBody());
            appInfoDes.addView(foldingTextView);
        }

        List<String> tagList = appIntroductionBean.getTagList();
        for(int i = 0 ; i < tagList.size() ; i ++){
            View labView = UIUtils.inflate(R.layout.appdetail_item_label_item) ;
            TextView tv = (TextView) labView.findViewById(R.id.appdetail_label_content_textview);
            tv.setText(tagList.get(i));
            flowLayout.addView(labView);
        }
        return view;
    }

    @Override
    protected void load() {
        appIntroductionPresenter.getAppIntroductionData(mActivity, packageName);
    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        List<String> images = appIntroductionBean.getImagesList();
        Intent intent = new Intent(getContext(), GalleryDetailActivity.class);
//        Intent intent = new Intent();
//        ComponentName cName = new ComponentName("com.alexandaking.myappstore","com.alexandaking.myappstore.mvp.view.activity.GalleryDetailActivity");
//        intent.setComponent(cName);
        intent.putExtra("tag",tag) ;
        intent.putStringArrayListExtra("urlList", (ArrayList<String>) images);
        getActivity().startActivity(intent) ;
        //getActivity().startActivityForResult(intent, Activity.RESULT_OK);
    }
}
