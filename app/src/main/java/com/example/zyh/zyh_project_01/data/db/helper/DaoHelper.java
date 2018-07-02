package com.example.zyh.zyh_project_01.data.db.helper;

import android.content.Context;

import com.example.zyh.zyh_project_01.data.db.manager.DaoManager;

/**
 * Created by zyh on 2018/7/2.
 */

public class DaoHelper<T> {
    private DaoManager manager;
    private Class<T> clazz;

    public DaoHelper(Context context){
        manager = DaoManager.getInstance(context);
    }

    //插入增加
    public boolean insert(T t){
        return manager.getDaoSession().insert(t) != -1;
    }
}
