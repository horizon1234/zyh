package com.example.zyh.zyh_project_01.data.db;

import com.example.zyh.zyh_project_01.utils.StringConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zyh on 2018/6/15.
 */
@Entity
public class Comic {
    @Id
    protected long id;
    protected String title;
    protected String cover;
    protected String author;
    //章节标题
    @Convert(columnType = String.class,converter = StringConverter.class)
    protected List<String> chapters;
    //章节url
    @Convert(columnType = String.class,converter = StringConverter.class)
    protected List<String> chapters_url;
    //标签
    @Convert(columnType = String.class,converter = StringConverter.class)
    protected List<String> tags;
    protected String collections;
    protected String describe;
    protected String point;
    protected String popularity;
    //话题量
    protected String topics;
    protected String updates;
    protected String status;
    //阅读方式
    protected int readType;
    protected int currentChapter;
    protected long collectTime;
    protected long clickTime;
    protected long downloadTime;
    protected boolean isCollected;
    //state状态数据库保存
    protected int stateInte;
    protected int current_page;
    protected int current_page_all;
    protected int download_num;
    protected int down_num_finish;
    protected int from;
    public int getFrom() {
        return this.from;
    }
    public void setFrom(int from) {
        this.from = from;
    }
    public int getDown_num_finish() {
        return this.down_num_finish;
    }
    public void setDown_num_finish(int down_num_finish) {
        this.down_num_finish = down_num_finish;
    }
    public int getDownload_num() {
        return this.download_num;
    }
    public void setDownload_num(int download_num) {
        this.download_num = download_num;
    }
    public int getCurrent_page_all() {
        return this.current_page_all;
    }
    public void setCurrent_page_all(int current_page_all) {
        this.current_page_all = current_page_all;
    }
    public int getCurrent_page() {
        return this.current_page;
    }
    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }
    public int getStateInte() {
        return this.stateInte;
    }
    public void setStateInte(int stateInte) {
        this.stateInte = stateInte;
    }
    public boolean getIsCollected() {
        return this.isCollected;
    }
    public void setIsCollected(boolean isCollected) {
        this.isCollected = isCollected;
    }
    public long getDownloadTime() {
        return this.downloadTime;
    }
    public void setDownloadTime(long downloadTime) {
        this.downloadTime = downloadTime;
    }
    public long getClickTime() {
        return this.clickTime;
    }
    public void setClickTime(long clickTime) {
        this.clickTime = clickTime;
    }
    public long getCollectTime() {
        return this.collectTime;
    }
    public void setCollectTime(long collectTime) {
        this.collectTime = collectTime;
    }
    public int getCurrentChapter() {
        return this.currentChapter;
    }
    public void setCurrentChapter(int currentChapter) {
        this.currentChapter = currentChapter;
    }
    public int getReadType() {
        return this.readType;
    }
    public void setReadType(int readType) {
        this.readType = readType;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getUpdates() {
        return this.updates;
    }
    public void setUpdates(String updates) {
        this.updates = updates;
    }
    public String getTopics() {
        return this.topics;
    }
    public void setTopics(String topics) {
        this.topics = topics;
    }
    public String getPopularity() {
        return this.popularity;
    }
    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }
    public String getPoint() {
        return this.point;
    }
    public void setPoint(String point) {
        this.point = point;
    }
    public String getDescribe() {
        return this.describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    public String getCollections() {
        return this.collections;
    }
    public void setCollections(String collections) {
        this.collections = collections;
    }
    public List<String> getTags() {
        return this.tags;
    }
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public List<String> getChapters_url() {
        return this.chapters_url;
    }
    public void setChapters_url(List<String> chapters_url) {
        this.chapters_url = chapters_url;
    }
    public List<String> getChapters() {
        return this.chapters;
    }
    public void setChapters(List<String> chapters) {
        this.chapters = chapters;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getCover() {
        return this.cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Generated(hash = 1247994581)
    public Comic(long id, String title, String cover, String author,
            List<String> chapters, List<String> chapters_url, List<String> tags,
            String collections, String describe, String point, String popularity,
            String topics, String updates, String status, int readType,
            int currentChapter, long collectTime, long clickTime,
            long downloadTime, boolean isCollected, int stateInte,
            int current_page, int current_page_all, int download_num,
            int down_num_finish, int from) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.author = author;
        this.chapters = chapters;
        this.chapters_url = chapters_url;
        this.tags = tags;
        this.collections = collections;
        this.describe = describe;
        this.point = point;
        this.popularity = popularity;
        this.topics = topics;
        this.updates = updates;
        this.status = status;
        this.readType = readType;
        this.currentChapter = currentChapter;
        this.collectTime = collectTime;
        this.clickTime = clickTime;
        this.downloadTime = downloadTime;
        this.isCollected = isCollected;
        this.stateInte = stateInte;
        this.current_page = current_page;
        this.current_page_all = current_page_all;
        this.download_num = download_num;
        this.down_num_finish = down_num_finish;
        this.from = from;
    }
    @Generated(hash = 1347984162)
    public Comic() {
    }
}
