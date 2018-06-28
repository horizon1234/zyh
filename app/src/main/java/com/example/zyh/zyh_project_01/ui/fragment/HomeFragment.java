package com.example.zyh.zyh_project_01.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zyh.zyh_project_01.R;
import com.example.zyh.zyh_project_01.ui.presenter.HomePresenter;
import com.example.zyh.zyh_project_01.ui.view.IHomeView;

/**
 * Created by zyh on 2018/6/15.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView {
    @Override
    protected void initPresenter() {
        mPresenter = new HomePresenter();
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_welcome,null);
    }

    @Override
    public void ShowToast(String t) {

    }
}
