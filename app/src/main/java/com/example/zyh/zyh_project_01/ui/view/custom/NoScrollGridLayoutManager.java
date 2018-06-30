package com.example.zyh.zyh_project_01.ui.view.custom;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

/**
 * Created by zyh on 2018/6/28.
 */

public class NoScrollGridLayoutManager extends GridLayoutManager{
    private boolean isScrollEnabled = true;

    public NoScrollGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public void setScrollEnabled(boolean flag){
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }
}
