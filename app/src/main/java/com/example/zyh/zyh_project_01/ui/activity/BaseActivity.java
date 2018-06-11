package com.example.zyh.zyh_project_01.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.zyh.zyh_project_01.ui.presenter.BasePresenter;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by zyh on 2018/6/6.
 */

public abstract class BaseActivity<P extends BasePresenter> extends RxAppCompatActivity {

    protected boolean isTrans;

    protected abstract void initPresenter(Intent intent);
    protected abstract int getLayout();
    protected abstract void initView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0){
            finish();
            return;
        }
        setContentView(getLayout());
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            //initStatusBar(true);
        }
        initPresenter(getIntent());
        //checkPresenterIsNull();
        initView();
    }

    public void initStatusBar(boolean isTransparent){
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if(isTransparent){
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        else{
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        isTrans = isTransparent;
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);

    }
}
