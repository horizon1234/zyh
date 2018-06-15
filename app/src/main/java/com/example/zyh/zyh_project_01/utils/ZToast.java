package com.example.zyh.zyh_project_01.utils;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.zyh.zyh_project_01.R;
import com.example.zyh.zyh_project_01.ui.custom.ToastLayout;

/**
 * Created by zyh on 2018/6/12.
 */

public class ZToast {
    private Activity mActivity;
    private RelativeLayout mToastLayout;
    private ToastLayout mToast;
    private ViewGroup mView;
    private String text;
    private long times;
    private static ZToast mToastInstance;

    public ZToast(Activity mActivity,String text,long times){
        this.mActivity = mActivity;
        this.text = text;
        this.times = times;
    }

    public ZToast(ViewGroup mView,String text,long times){
        this.mView = mView;
        this.text = text;
        this.times = times;
    }

    public static ZToast makeText(Activity mActivity,String text,long times){
        mToastInstance = new ZToast(mActivity,text,times);
        return mToastInstance;
    }

    public static ZToast makeText(ViewGroup mView,String text,long times){
        mToastInstance = new ZToast(mView,text,times);
        return mToastInstance;
    }

    public void show(){
        if(mActivity != null){
            mToastLayout = mActivity.findViewById(R.id.rl_toast);
            if(mToastLayout == null){
                mToast = new ToastLayout(mActivity);
                mActivity.addContentView(mToast,new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(mActivity,60)));
            }else {
                mToast = (ToastLayout) mToastLayout.getParent();
            }
            mToast.setContent(text);
            mToast.showToast(times);
            return;
        }else if(mView != null){
            mToastLayout = mView.findViewById(R.id.rl_toast);
            if(mToastLayout == null){
                mToast = new ToastLayout(mView.getContext());
                mView.addView(mToast,new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(mView.getContext(),60)));
            }else{
                mToast = (ToastLayout) mToastLayout.getParent();
            }
            mToast.setContent(text);
            mToast.showToast(times);
        }
    }
}
