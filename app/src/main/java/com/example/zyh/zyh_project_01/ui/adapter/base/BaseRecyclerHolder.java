package com.example.zyh.zyh_project_01.ui.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zyh.zyh_project_01.utils.GlideImageLoader;

/**
 * Created by zyh on 2018/6/28.
 */

public class BaseRecyclerHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;
    private Context context;

    public BaseRecyclerHolder(Context context,View itemView) {
        super(itemView);
        this.context = context;
        views = new SparseArray<>(8);
    }

    /**
     * 取得一个RecyclerHolder对象
     * @param context 上下文
     * @param itemView 子项
     * @return 返回一个RecyclerHolder对象
     */
    public static BaseRecyclerHolder getRecyclerHolder(Context context,View itemView){
        return new BaseRecyclerHolder(context,itemView);
    }

    /**
     * 通过view的id获取对应的控件，如果没有则加入views中
     * @param viewId 控件的id
     * @return 返回一个控件
     */
    public <T extends View> T getView(int viewId){
        View view = views.get(viewId);
        if(view == null){
            view = itemView.findViewById(viewId);
            views.put(viewId,view);
        }
        return (T) view;
    }

    public BaseRecyclerHolder setVisibility(int viewId,int visible){
        View view = getView(viewId);
        view.setVisibility(visible);
        return this;
    }

    public BaseRecyclerHolder setText(int viewId,String text){
        TextView tv = getView(viewId);
        if(text == null){
            tv.setVisibility(View.GONE);
        }else {
            tv.setVisibility(View.VISIBLE);
            tv.setText(text);
        }
        return this;
    }

    public BaseRecyclerHolder setImageByUrl(int viewId,String url){
        ImageView iv = getView(viewId);
        GlideImageLoader.loadImage(context,url,iv);
        return this;
    }


}
