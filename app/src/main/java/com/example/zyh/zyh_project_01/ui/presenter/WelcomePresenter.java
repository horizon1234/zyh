package com.example.zyh.zyh_project_01.ui.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import com.example.zyh.zyh_project_01.MainActivity;
import com.example.zyh.zyh_project_01.data.commons.Constants;
import com.example.zyh.zyh_project_01.ui.view.IWelcomeView;
import com.example.zyh.zyh_project_01.utils.FileUtil;
import com.orhanobut.hawk.Hawk;


/**
 * Created by zyh on 2018/6/11.
 */

public class WelcomePresenter extends BasePresenter<IWelcomeView> {
    private Handler mhandler;
    private Activity mContext;
    private boolean isShowAd;
    private closeRunnable runnable;

    public WelcomePresenter(Activity context,IWelcomeView view){
        super(context,view);
        mhandler = new Handler(context.getMainLooper());
        mContext = context;
        runnable = new closeRunnable();
        isShowAd = false;
    }


    public void init(){
        mhandler.postDelayed(runnable,2000);
        FileUtil.init();
        long lasttime = Hawk.get(Constants.LAST_START_TIME,0L);
        if(lasttime != 0 && getCurrentTime() - lasttime >= 24*60*60){
            //大于1天 清除缓存
        }
        Hawk.put(Constants.LAST_START_TIME,getCurrentTime());
    }

    public void onDestory(){
        if(!isShowAd){
            mhandler.removeCallbacks(runnable);
        }
    }

    class closeRunnable implements Runnable{
        @Override
        public void run() {
            mContext.finish();
            Intent i = new Intent(mContext, MainActivity.class);
            mContext.startActivity(i);
        }
    }

}
