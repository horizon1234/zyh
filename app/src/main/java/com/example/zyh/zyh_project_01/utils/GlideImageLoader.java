package com.example.zyh.zyh_project_01.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zyh.zyh_project_01.R;
import com.example.zyh.zyh_project_01.data.db.Comic;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by zyh on 2018/6/28.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Comic comic = (Comic) path;
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context)
                .load(comic.getCover())
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }

    public static void loadImage(Context context,String url,ImageView imageView){
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }
}
