package com.example.zyh.zyh_project_01.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import com.example.zyh.zyh_project_01.R;
import com.example.zyh.zyh_project_01.ui.presenter.WelcomePresenter;
import com.example.zyh.zyh_project_01.ui.view.IWelcomeView;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView{

    @Override
    protected void initPresenter(Intent intent) {
        mPresenter = new WelcomePresenter(this,this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        mPresenter.init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            return;
        }
        mPresenter.onDestory();
    }

    @Override
    public void ShowToast(String t) {
        showToast(t);
    }

}
