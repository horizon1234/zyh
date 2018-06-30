package com.example.zyh.zyh_project_01.data.entity;

import com.example.zyh.zyh_project_01.data.db.Comic;

/**
 * Created by zyh on 2018/6/28.
 */

public class HomeTitle extends Comic {
    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public int getTitleType() {
        return TitleType;
    }

    public void setTitleType(int titleType) {
        TitleType = titleType;
    }

    public String itemTitle;
    public int TitleType;

    public HomeTitle(){}

    public HomeTitle(String itmeTitle){
        this.itemTitle = itmeTitle;
    }

}
