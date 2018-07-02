package com.example.zyh.zyh_project_01.utils;

import com.example.zyh.zyh_project_01.data.entity.LargeHomeItem;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zyh on 2018/7/2.
 */

public class TecentComicAnalysis {

    public static List<LargeHomeItem> TransToRecommendComic(Document doc){
        List<LargeHomeItem> mdats = new ArrayList<>();
        List<Element> detail = doc.getElementsByAttributeValue("class","in-anishe-text");
        Random random = new Random();
        int result = random.nextInt(5);
        for(int i = (result *6); i <(result + 1) * 6; i ++){
            LargeHomeItem comic = new LargeHomeItem();
            comic.setTitle(detail.get(i).select("a").attr("title"));
            comic.setCover(detail.get(i));
        }
    }
}
