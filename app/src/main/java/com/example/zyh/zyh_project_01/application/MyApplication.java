package com.example.zyh.zyh_project_01.application;

import android.app.Application;

import com.example.zyh.zyh_project_01.utils.LogUtil;
import com.orhanobut.hawk.Hawk;

/**
 * Created by zyh on 2018/6/5.
 * 自定义Application，在这里做一些初始化框架等操作
 * 1、Hawk 一个安全简单的做key value存储的库
 * Initialize Hawk.init(context).build();
 * Usage Save any type  Hawk.put(key,T);
 *  Get original value with original type T value = Hawk.get(key)
 *  Delete any entry Hawk.delete(key)
 *  Check if any key exists Hawk.contains(key)
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Hawk初始化
        Hawk.init(this).build();
        LogUtil.init(LogUtil.VERBOSE,LogUtil.TAG );
    }
}
