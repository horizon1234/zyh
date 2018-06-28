package com.example.zyh.zyh_project_01.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zyh.zyh_project_01.ui.activity.BaseFragmentActivity;
import com.example.zyh.zyh_project_01.ui.presenter.BasePresenter;
import com.example.zyh.zyh_project_01.utils.ZToast;

import butterknife.ButterKnife;

/**
 * Created by zyh on 2018/6/15.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    protected P mPresenter;
    BaseFragmentActivity mActivity;
    protected abstract void initPresenter();
    protected abstract int getLayoutId();
    protected abstract void initView(View view,Bundle savedInstanceState);
    protected abstract void initData();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseFragmentActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        ButterKnife.bind(this,view);
        initPresenter();
        checkPresentIsNull();
        initView(view,savedInstanceState);
        return view;
    }

    private void checkPresentIsNull(){
        if(mPresenter == null){
            throw new IllegalStateException("please init presneter");
        }
    }

    public void showToast(String text){
        ZToast.makeText(mActivity, text,1000).show();
        //Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show();
    }
}
