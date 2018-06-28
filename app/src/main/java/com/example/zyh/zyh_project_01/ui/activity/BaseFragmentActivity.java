package com.example.zyh.zyh_project_01.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseFragmentActivity extends RxAppCompatActivity {
    protected android.support.v4.app.FragmentManager fragmentManager;
    protected android.support.v4.app.FragmentTransaction fragmentTransaction;
    protected List<Fragment> fragments;
    protected boolean isTrans;

    public boolean isTrans(){
        return isTrans;
    }

    protected abstract void initView();
    protected abstract int getLayoutId();
    protected abstract void initData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            initStatusBar(true);
        }
        ButterKnife.bind(this);
        initView();
        initData();
    }

    protected void initStatusBar(Boolean isTransparent){
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if(isTransparent){
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }else{
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        isTrans = isTransparent;
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    public void selectTab(int num){
        fragmentTransaction = fragmentManager.beginTransaction();
        for (int i =0; i < fragments.size(); i++){
            fragmentTransaction.hide(fragments.get(i));
        }
        fragmentTransaction.show(fragments.get(num));
    }
}
