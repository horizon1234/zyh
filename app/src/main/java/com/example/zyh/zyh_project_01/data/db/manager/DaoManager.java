package com.example.zyh.zyh_project_01.data.db.manager;

import android.content.Context;

import com.example.zyh.zyh_project_01.data.commons.Constants;
import com.example.zyhh.green.gen.DaoMaster;
import com.example.zyhh.green.gen.DaoSession;

/**
 * Created by zyh on 2018/6/15.
 * 数据库统一管理者 单例模式
 */

public class DaoManager {

    private static volatile DaoManager manager;
    private Context mContext;
    private static DaoMaster.DevOpenHelper helper;
    private static DaoSession daoSession;
    private static DaoMaster daoMaster;

    private DaoManager(Context context){
        this.mContext = context.getApplicationContext();
    }
    public static DaoManager getInstance(Context context){
        if(manager == null){
            synchronized (DaoManager.class){
                if(manager == null){
                    manager = new DaoManager(context);
                }
            }
        }
        return manager;
    }

    public DaoMaster getDaoMaster(){
        if(daoMaster == null){
            helper = new DaoMaster.DevOpenHelper(mContext, Constants.DB_NAME,null);
            daoMaster = new DaoMaster(helper.getWritableDb());
        }
        return daoMaster;
    }

    public DaoSession getDaoSession(){
        if(daoSession == null){
            if(daoMaster == null){
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    public void closeHelper(){
        if(helper != null){
            helper.close();
            helper = null;
        }
    }

    public void closeSession(){
        if(daoSession != null){
            daoSession.clear();
            daoSession = null;
        }
    }
}
