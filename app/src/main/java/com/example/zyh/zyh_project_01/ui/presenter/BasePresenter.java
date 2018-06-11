package com.example.zyh.zyh_project_01.ui.presenter;

import android.app.Activity;

import com.example.zyh.zyh_project_01.ui.view.IBaseView;

import java.util.Date;

/**
 * Created by zyh on 2018/6/6.
 */

public abstract class BasePresenter<GV extends IBaseView> {

    protected GV mView;
    protected Activity mContext;

    public BasePresenter(Activity context,GV view){
        mContext = context;
        mView = view;
    }

    protected BasePresenter(){

    }

    public long getCurrentTime(){
        Date date = new Date();
        long datetime = date.getTime();
        return datetime;
    }
}
