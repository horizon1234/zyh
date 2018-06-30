package com.example.zyh.zyh_project_01.ui.view.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.zyh.zyh_project_01.R;
import com.example.zyh.zyh_project_01.utils.DisplayUtil;

/**
 * Created by zyh on 2018/6/15.
 *
 */

public class ZElasticRefreshScrollView extends ScrollView {

    private RelativeLayout inner;
    private RelativeLayout mMoveView;
    private View mLoadingBottom;
    private View mTopView;
    private float y;
    private Rect normal = new Rect();
    private int height;
    private boolean isResfrsh;
    private RefreshListener listener;
    private RelativeLayout mLoadingTop;
    private ImageView mLoadingTopImg;
    private TextView mLoadingText;
    public static final int SCROLL_TO_UP = 1;
    public static final int SCROLL_TO_DOWN = 2;
    int oldY = 0;

    public interface RefreshListener{
        void onActionDown();
        void onActionUp();
        void onRefresh();
        void onRefreshFinish();
        void onLoadMore();
        void onScorll(int y);
        void onAlphaActionBar(float a);
    }

    public ZElasticRefreshScrollView(Context context) {
        this(context,null);
    }

    public ZElasticRefreshScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ZElasticRefreshScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*
    * 这个是渲染之后回调的函数
    * */
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onFinishInflate() {
        if(getChildCount() > 0){
            inner = (RelativeLayout) getChildAt(0);
        }
        mMoveView = (RelativeLayout) inner.getChildAt(0);
        mTopView = inner.getChildAt(1);
        mLoadingTop = (RelativeLayout) mMoveView.getChildAt(0);
        mLoadingText = (TextView) ((LinearLayout) mLoadingTop.getChildAt(0)).getChildAt(1);
        mLoadingBottom = mMoveView.getChildAt(3);
        mLoadingTopImg = (ImageView) inner.findViewById(R.id.iv_loading_top);
        initAnimation();
    }

    private void initAnimation(){
        mLoadingTopImg.setImageResource(R.drawable.background_home_recent);
        AnimationDrawable animationDrawable = (AnimationDrawable) mLoadingTopImg.getDrawable();
        animationDrawable.start();
    }

    //刷新完成的调用由界面来调用
    public void setRefreshing(boolean isResfrsh){
        if(this.isResfrsh != isResfrsh){
            this.isResfrsh = isResfrsh;
            if(isResfrsh == false){
                RefreshAnimationFinish();
            }
        }
    }

    private void RefreshAnimationFinish(){
        TranslateAnimation ta = new TranslateAnimation(0,
                0,
                mMoveView.getTop() - DisplayUtil.dip2px(getContext(),200),
                normal.top - DisplayUtil.dip2px(getContext(),200));
        Interpolator in = new DecelerateInterpolator();
        ta.setInterpolator(in);
        ta.setDuration(300);
        ta.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                listener.onRefreshFinish();
                mLoadingText.setText("下拉刷新");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mMoveView.startAnimation(ta);
        mLoadingText.setText("刷新完成");
        mMoveView.layout(normal.left,normal.top,normal.right,normal.bottom);
        normal.setEmpty();
    }

    @Override
    protected void onScrollChanged(int l, int y, int oldl, int oldt) {
        super.onScrollChanged(l, y, oldl, oldt);
        height = inner.getMeasuredHeight() - mLoadingBottom.getMeasuredHeight() - DisplayUtil.getScreenHeight(getContext());
        if(y > height){
            this.scrollTo(0,height);
        }
        if(y < mTopView.getHeight() - DisplayUtil.dip2px(getContext(),60)){
            listener.onAlphaActionBar(0f);
        }else if(y < mTopView.getHeight()){
            listener.onAlphaActionBar((float)(y-(mTopView.getHeight()-DisplayUtil.dip2px(getContext(),60)))/DisplayUtil.dip2px(getContext(),60));
        }else{
            listener.onAlphaActionBar(1f);
        }

        if(y - oldY > 0 && y < height){
            listener.onScorll(SCROLL_TO_UP);
        }else if(y - oldY <0 ){
            listener.onScorll(SCROLL_TO_UP);
        }
        oldY = y;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(inner == null){
            return super.onTouchEvent(ev);
        }else{
            if(isResfrsh && listener != null){
                return super.onTouchEvent(ev);
            }
            commOnTouchEvent(ev);
            if(mMoveView.getTop() > DisplayUtil.dip2px(getContext(),200) && getScrollY() ==0
                    || mMoveView.getBottom() < inner.getMeasuredHeight()
                    && getScrollY() ==inner.getMeasuredHeight()-mLoadingBottom.getMeasuredHeight()-DisplayUtil.getScreenHeight(getContext())){
                return false;
            }
            return super.onTouchEvent(ev);
        }
    }

    public void commOnTouchEvent(MotionEvent ev){
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                y = ev.getY();
                mLoadingText.setText("下拉刷新");
                listener.onActionDown();
                break;
            case MotionEvent.ACTION_UP:
                listener.onActionUp();
                if(isNeedAnimaion()){
                    if(mMoveView.getTop() > DisplayUtil.dip2px(getContext(),245)
                            && listener != null){
                        RefreshAnimation();
                        mLoadingText.setText("努力加载中。。。");
                        isResfrsh = true;
                        listener.onRefresh();
                    }else{
                        if(getScrollY() == height && listener != null){
                            listener.onLoadMore();
                        }
                        animation();
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                final float preY = y;
                float nowY = ev.getY();
                int deltaY;
                deltaY = (int) Math.abs(nowY - preY);
                if(isNeedMove(nowY)){
                    if(normal.isEmpty()){
                        normal.set(mMoveView.getLeft(),mMoveView.getTop(),mMoveView.getRight(),mMoveView.getBottom());
                        return;
                    }
                    if(nowY > preY){
                        mMoveView.layout(mMoveView.getLeft(),mMoveView.getTop() + deltaY,
                                mMoveView.getRight(),mMoveView.getBottom() + deltaY);
                    }else if(nowY < preY){
                        mMoveView.layout(mMoveView.getLeft(),mMoveView.getTop()-deltaY,
                                mMoveView.getRight(),mMoveView.getBottom()-deltaY);
                    }
                    if(mMoveView.getTop() > DisplayUtil.dip2px(getContext(),245) && listener != null){
                        mLoadingText.setText("松开即可刷新");
                    }
                }
                break;
            default:
                break;
        }
    }

    public void animation(){
        TranslateAnimation ta = new TranslateAnimation(0,
                0,
                mMoveView.getTop() - DisplayUtil.dip2px(getContext(),200),
                normal.top - DisplayUtil.dip2px(getContext(),200));
        Interpolator in = new DecelerateInterpolator();
        ta.setInterpolator(in);
        ta.setDuration(300);
        mMoveView.startAnimation(ta);
        mMoveView.layout(normal.left,normal.top,normal.right,normal.bottom);
        normal.setEmpty();
    }

    public boolean isNeedMove(float nowY){
        float scrollY = getScrollY();
        return scrollY == 0 && nowY > mTopView.getMeasuredHeight() || scrollY == height;
    }

    public void RefreshAnimation(){
        TranslateAnimation ta = new TranslateAnimation(0,
                0,
                mMoveView.getTop() - DisplayUtil.dip2px(getContext(),245),
                normal.top - DisplayUtil.dip2px(getContext(),200));
        Interpolator in = new DecelerateInterpolator();
        ta.setInterpolator(in);
        ta.setDuration(300);
        mMoveView.startAnimation(ta);
        mMoveView.layout(normal.left,
                normal.top + DisplayUtil.dip2px(getContext(),53),
                    normal.right,
                normal.bottom + DisplayUtil.dip2px(getContext(),53));
    }

    public boolean isNeedAnimaion(){
        return !normal.isEmpty();
    }

    public void setResfrshListener(RefreshListener listener){
        this.listener = listener;
    }
}
