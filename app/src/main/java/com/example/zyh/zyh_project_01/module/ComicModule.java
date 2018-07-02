package com.example.zyh.zyh_project_01.module;

import android.content.Context;

import com.example.zyh.zyh_project_01.data.commons.Constants;
import com.example.zyh.zyh_project_01.data.commons.Url;
import com.example.zyh.zyh_project_01.data.db.Comic;
import com.example.zyh.zyh_project_01.data.db.helper.DaoHelper;
import com.example.zyh.zyh_project_01.data.entity.HomeTitle;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;

/**
 * Created by zyh on 2018/7/2.
 */

public class ComicModule {
    private DaoHelper mHeper;
    private RxAppCompatActivity rxAppCompatActivity;

    public ComicModule(Context context){
        rxAppCompatActivity = (RxAppCompatActivity) context;
        mHeper = new DaoHelper(context);
    }

    public void getData(Observer<List<Comic>> observer){
        Observable ComicListObservable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                List<Comic> mdats = new ArrayList<>();
                Document recommend = Jsoup.connect(Url.TencentHomePage).get();
                Document japan = Jsoup.connect(Url.TencentJapanHot).get();
                Document doc = Jsoup.connect(Url.TencentTopUrl+"1").get();

            }
        });
    }

    private void addComic(Document doc,List<Comic> mdats,int type){
        HomeTitle homeTitle;
        switch (type){
            case Constants.TYPE_RECOMMEND:
                homeTitle = new HomeTitle();
                homeTitle.setItemTitle("强推作品");
                homeTitle.setTitleType(Constants.TYPE_RECOMMEND);
                mdats.add(homeTitle);
                mdats.addAll();
        }
    }
}
