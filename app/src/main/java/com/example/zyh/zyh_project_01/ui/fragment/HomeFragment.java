package com.example.zyh.zyh_project_01.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zyh.zyh_project_01.MainActivity;
import com.example.zyh.zyh_project_01.R;
import com.example.zyh.zyh_project_01.data.db.Comic;
import com.example.zyh.zyh_project_01.ui.adapter.MainAdapter;
import com.example.zyh.zyh_project_01.ui.presenter.HomePresenter;
import com.example.zyh.zyh_project_01.ui.view.IHomeView;
import com.example.zyh.zyh_project_01.ui.view.custom.NoScrollGridLayoutManager;
import com.example.zyh.zyh_project_01.ui.view.custom.ZElasticRefreshScrollView;
import com.example.zyh.zyh_project_01.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zyh on 2018/6/15.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView<Comic>,MainAdapter.OnItemClickListener {

    @BindView(R.id.sv_comic)
    ZElasticRefreshScrollView mScrollView;
    @BindView(R.id.B_banner)
    Banner mBanner;
    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.rl_error_view)
    RelativeLayout mErrorView;
    @BindView(R.id.iv_error)
    ImageView mReload;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mActionBar;
    @BindView(R.id.v_actionbar_bg)
    View mActionBarBg;
    @BindView(R.id.tv_recent)
    TextView mTvRecent;
    @BindView(R.id.rl_recent)
    RelativeLayout mRlRecent;
    @BindView(R.id.tv_hometitle1)//action的标题
            TextView mHomeTitle1;
    @BindView(R.id.tv_hometitle2)
    TextView mHomeTitle2;
    @BindView(R.id.iv_search)
    ImageView mSearch;


    MainActivity activity;
    private MainAdapter mAdapter;


    @Override
    protected void initPresenter() {
        mPresenter = new HomePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);

        NoScrollGridLayoutManager layoutManager = new NoScrollGridLayoutManager(activity,6);
        layoutManager.setScrollEnabled(false);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new MainAdapter(mActivity,R.layout.item_hometitle,
                R.layout.item_homepage_three,R.layout.item_homepage,
                R.layout.item_homepage_full);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);


    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            mScrollView.scrollTo(0,0);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void ShowToast(String t) {
        showToast(t);
    }


    @Override
    public void fillData(List<Comic> data) {
       if(data != null && data.size() != 0){
           mAdapter.updateWithClear(data);
       } else {
           ShowToast("未获取到数据");
       }
    }

    @Override
    public void appendMoreDataToView(List<Comic> data) {
        if(data != null && data.size() != 0){
            mAdapter.update(data);
        }else {
            ShowToast("未获取到数据");
        }
    }

    @Override
    public void hasNoMoreData() {
        ShowToast("没有数据了");
    }

    @Override
    public void showErrorView(String throwable) {
        mScrollView.setRefreshing(false);
        mErrorView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void getDataFinish() {
        mScrollView.setRefreshing(false);
        mAdapter.notifyDataSetChanged();
        mErrorView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefreshFinish() {
        mScrollView.setRefreshing(false);
        if(mErrorView.isShown()){
            mErrorView.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void fillBanner(List<Comic> data) {
        mBanner.setImages(data);
        mBanner.start();
    }

    @Override
    public void fillRecent(String str) {
        if(str != null){
            mRlRecent.setVisibility(View.VISIBLE);
            mTvRecent.setText(str);
        }else{
            mRlRecent.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(RecyclerView parent, View view, int position) {
        Comic comic = mAdapter.getItems(position);

    }

    @Override
    public void onTitleClick(RecyclerView parent, View view, int type) {
        switch (type){

        }
    }
}
