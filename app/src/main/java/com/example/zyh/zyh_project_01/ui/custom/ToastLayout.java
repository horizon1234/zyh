package com.example.zyh.zyh_project_01.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zyh.zyh_project_01.R;
import com.example.zyh.zyh_project_01.utils.DisplayUtil;

/**
 * Created by zyh on 2018/6/12.
 */

public class ToastLayout extends RelativeLayout {
    private static final int ANIMATION_TIME = 200;
    private View view;
    private TextView mContent;
    private boolean isShow;

    public ToastLayout(Context context) {
        this(context,null);
    }

    public ToastLayout(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ToastLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = LayoutInflater.from(getContext()).inflate(R.layout.layout_toast,null);
        addView(view);
        mContent = view.findViewById(R.id.tv_content);
    }

    public boolean isShow(){
        return isShow;
    }

    public void setContent(String string){
        if(mContent != null){
            mContent.setText(string);
        }
    }

    public void showToast(long time){
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation trans1 = new TranslateAnimation(0, 0 ,-DisplayUtil.dip2px(getContext(),60) ,0);
        TranslateAnimation trans2 = new TranslateAnimation(0,0 , 0 , -DisplayUtil.dip2px(getContext(),60));
        trans1.setDuration(ANIMATION_TIME);
        trans2.setStartOffset(ANIMATION_TIME+time);
        trans2.setDuration(ANIMATION_TIME);
        animationSet.addAnimation(trans1);
        animationSet.addAnimation(trans2);
        this.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isShow = true;
                ToastLayout.this.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isShow = false;
                ToastLayout.this.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
