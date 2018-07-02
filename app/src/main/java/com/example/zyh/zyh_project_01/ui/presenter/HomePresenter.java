package com.example.zyh.zyh_project_01.ui.presenter;

import android.app.Activity;

import com.example.zyh.zyh_project_01.data.db.Comic;
import com.example.zyh.zyh_project_01.data.db.helper.DaoHelper;
import com.example.zyh.zyh_project_01.module.ComicModule;
import com.example.zyh.zyh_project_01.ui.view.IHomeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyh on 2018/6/15.
 */

public class HomePresenter extends BasePresenter<IHomeView> {
    private ComicModule mModel;
    private List<Comic> mBanners,mDatas;
    private DaoHelper mHelper;
    private Comic recentComic;

    public List<Comic> getmBanners(){
        return mBanners;
    }

    public List<Comic> getmDatas(){
        return mDatas;
    }

    public HomePresenter(Activity context,IHomeView view){
        super(context ,view);
        mView = view;
        mHelper = new DaoHelper(context);
        mBanners = new ArrayList<>();
        mDatas = new ArrayList<>();
        mModel = new ComicModule(context);
    }

    public void loadData(){}

    public void loadMoreData(){}

    public void toRecentComic(){}

    public void getRecent(){}
}
