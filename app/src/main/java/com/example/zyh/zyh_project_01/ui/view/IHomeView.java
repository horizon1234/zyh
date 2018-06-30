package com.example.zyh.zyh_project_01.ui.view;

import com.example.zyh.zyh_project_01.data.db.Comic;

import java.util.List;

/**
 * Created by zyh on 2018/6/15.
 */

public interface IHomeView<T extends Comic> extends IBaseView{
    void fillData(List<Comic> data);


    void appendMoreDataToView(List<T> data);
    void hasNoMoreData();
    void showErrorView(String throwable);
    void getDataFinish();

    void onRefreshFinish();
    void fillBanner(List<T> data);

    void fillRecent(String str);
}
