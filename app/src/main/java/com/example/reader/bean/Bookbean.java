package com.example.reader.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

public class Bookbean extends DataSupport implements Serializable {
/*
    {
        "total": 40215,
            "books": [
        {
            "_id": "5816b415b06d1d32157790b1",
                "title": "圣墟",
                "author": "辰东",
                "shortIntro": "在破败中崛起，在寂灭中复苏。沧海成尘，雷电枯竭，那一缕幽雾又一次临近大地，世间的枷锁被打开了，一个全新的世界就此揭开神秘的一角……",
                "cover": "/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F1228859%2F_1228859_441552.jpg%2F",
                "site": "zhuishuvip",
                "majorCate": "玄幻",
                "minorCate": "东方玄幻",
                "sizetype": -1,
                "superscript": "",
                "contentType": "txt",
                "allowMonthly": false,
                "banned": 0,
                "latelyFollower": 305266,
                "retentionRatio": 72.08,
                "lastChapter": "第629章 闷棍镇压昆仑",
                "tags": [
            "玄幻",
                    "东方玄幻"
            ]
        }...
    ],
        "ok": true
    }
*/
    private String _id;//"5816b415b06d1d32157790b1",
    private String title;//"圣墟",
    private String author;//"辰东",
    private String shortIntro;// "在破败中崛起，在寂灭中复苏。沧海成尘，雷电枯竭，那一缕幽雾又一次临近大地，世间的枷锁被打开了，一个全新的世界就此揭开神秘的一角……",
    private String cover;//"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F1228859%2F_1228859_441552.jpg%2F",
    private String site;//"zhuishuvip",
    private String majorCate;//"minorCate": "东方玄幻",
    private String minorCate;//"东方玄幻",
    private int sizetype;//"sizetype": -1,
    private String superscript;//"superscript": "",
    private String contentType;// "txt",
    private boolean allowMonthly;//false,
    private int latelyFollower;//305266,
    private int banned;//"banned": 0,
    private String retentionRatio;//"retentionRatio": 72.08,保留比例
    private String lastChapter;// "第629章 闷棍镇压昆仑",
    private List<String> tags;// "玄幻", "东方玄幻"

    public Bookbean(String _id,String cover,String title,String author,String majorCate,
                    String minorCate, int latelyFollower,String retentionRatio){
        this._id=_id;
        this.cover=cover;
        this.title=title;
        this.author=author;
        this.majorCate=majorCate;
        this.minorCate=minorCate;
        this.latelyFollower=latelyFollower;
        this.retentionRatio=retentionRatio;
    }
    public Bookbean(String title,String author,String majorCate,
                    String minorCate, int latelyFollower,String retentionRatio){

        this.cover=cover;
        this.title=title;
        this.author=author;
        this.majorCate=majorCate;
        this.minorCate=minorCate;
        this.latelyFollower=latelyFollower;
        this.retentionRatio=retentionRatio;
    }

    public Bookbean(){

    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getShortIntro() {
        return shortIntro;
    }

    public void setShortIntro(String shortIntro) {
        this.shortIntro = shortIntro;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getMajorCate() {
        return majorCate;
    }

    public void setMajorCate(String majorCate) {
        this.majorCate = majorCate;
    }

    public String getMinorCate() {
        return minorCate;
    }

    public void setMinorCate(String minorCate) {
        this.minorCate = minorCate;
    }

    public int getSizetype() {
        return sizetype;
    }

    public void setSizetype(int sizetype) {
        this.sizetype = sizetype;
    }

    public String getSuperscript() {
        return superscript;
    }

    public void setSuperscript(String superscript) {
        this.superscript = superscript;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean isAllowMonthly() {
        return allowMonthly;
    }

    public void setAllowMonthly(boolean allowMonthly) {
        this.allowMonthly = allowMonthly;
    }

    public int getLatelyFollower() {
        return latelyFollower;
    }

    public void setLatelyFollower(int latelyFollower) {
        this.latelyFollower = latelyFollower;
    }

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }

    public String getRetentionRatio() {
        return retentionRatio;
    }

    public void setRetentionRatio(String  retentionRatio) {
        this.retentionRatio = retentionRatio;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
