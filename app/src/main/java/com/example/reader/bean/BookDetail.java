package com.example.reader.bean;

import java.io.Serializable;
import java.util.List;

public class BookDetail implements Serializable {
//             "_id": "5816b415b06d1d32157790b1",
//             "title": "圣墟",
//             "author": "辰东",
//             "longIntro": "在破败中崛起，在寂灭中复苏。沧海成尘，雷电枯竭，那一缕幽雾又一次临近大地，世间的枷锁被打开了，一个全新的世界就此揭开神秘的一角……",
//             "cover": "/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F1228859%2F_1228859_441552.jpg%2F",
//             "majorCate": "玄幻",
//             "minorCate": "东方玄幻",
//             "creater": "iPhone 5s (UK+Europe+Asis+China)",
//             "rating": {
//              "count": 17432,
//                "score": 8.686,
//                "isEffect": true
//    },
//            "sizetype": -1,

//            "superscript": "",
//            "currency": 0,
//            "contentType": "txt",
//            "_le": false,
//            "allowMonthly": false,
//            "allowVoucher": true,
//            "allowBeanVoucher": false,
//            "hasCp": true,
//            "postCount": 49851,
//            "latelyFollower": 293174,

//            "followerCount": 0,
//            "wordCount": 2748484,
//            "serializeWordCount": 8232,
//            "retentionRatio": "71.08",
//            "updated": "2017-10-25T12:28:56.605Z",
//            "isSerial": true,
//            "chaptersCount": 716,
//            "lastChapter": "第716章 严肃表态负责",
//            "gender": [
//            "male"
//            ],
//            "tags": [
//            "玄幻",
//            "东方玄幻"
//            ],

//            "cat": "东方玄幻",
//            "donate": false,
//            "copyright": "阅文集团正版授权",
//            "_gg": false,
//            "discount": null

    private String _id;//"5816b415b06d1d32157790b1",
    private String title;//"圣墟",
    private String author;//"辰东",
    private String longIntro;// "在破败中崛起，在寂灭中复苏。沧海成尘，雷电枯竭，那一缕幽雾又一次临近大地，世间的枷锁被打开了，一个全新的世界就此揭开神秘的一角……",
    private String cover;//"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F1228859%2F_1228859_441552.jpg%2F",

    private String majorCate;//"minorCate": "东方玄幻",
    private String minorCate;//"东方玄幻",
    private String creater;
    private Rating rating;
    private int sizetype;//"sizetype": -1,

    private String superscript;//"superscript": "",
    private int currency;
    private String contentType;// "txt",
    private boolean _le;
    private boolean allowMonthly;//false,

    private boolean allowVoucher;
    private boolean allowBeanVoucher;
    private boolean hasCp;
    private int postCount;
    private int latelyFollower;//305266,

    private int followerCount;

    private int wordCount;
    private int serializeWordCount;
    private String retentionRatio;//"retentionRatio": 72.08,保留比例
    private String updated;
    private boolean isSerial;

    private int chaptersCount;
    private String lastChapter;// "第629章 闷棍镇压昆仑",
    private List<String> gender;
    private List<String> tags;// "玄幻", "东方玄幻"
    private String cat;

    private boolean donate;
    private String copyright;
    private boolean _gg;
    private String discount;

    public BookDetail(){

    }

    public BookDetail(String _id, String title, String author, String longIntro, String cover,
                      String majorCate, String minorCate, int postCount, int latelyFollower,
                      int followerCount, int wordCount, int serializeWordCount, String retentionRatio,
                      String updated, int chaptersCount, String lastChapter,List<String> tags) {
        this._id = _id;
        this.title = title;
        this.author = author;
        this.longIntro = longIntro;
        this.cover = cover;
        this.majorCate = majorCate;
        this.minorCate = minorCate;
        this.postCount = postCount;
        this.latelyFollower = latelyFollower;
        this.followerCount = followerCount;
        this.wordCount = wordCount;
        this.serializeWordCount = serializeWordCount;
        this.retentionRatio = retentionRatio;
        this.updated = updated;
        this.chaptersCount = chaptersCount;
        this.lastChapter = lastChapter;
        this.tags=tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLongIntro() {
        return longIntro;
    }

    public void setLongIntro(String longIntro) {
        this.longIntro = longIntro;
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

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
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

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public int getLatelyFollower() {
        return latelyFollower;
    }

    public void setLatelyFollower(int latelyFollower) {
        this.latelyFollower = latelyFollower;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public int getSerializeWordCount() {
        return serializeWordCount;
    }

    public void setSerializeWordCount(int serializeWordCount) {
        this.serializeWordCount = serializeWordCount;
    }

    public String getRetentionRatio() {
        return retentionRatio;
    }

    public void setRetentionRatio(String retentionRatio) {
        this.retentionRatio = retentionRatio;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean is_le() {
        return _le;
    }

    public void set_le(boolean _le) {
        this._le = _le;
    }

    public boolean isAllowMonthly() {
        return allowMonthly;
    }

    public void setAllowMonthly(boolean allowMonthly) {
        this.allowMonthly = allowMonthly;
    }

    public boolean isAllowVoucher() {
        return allowVoucher;
    }

    public void setAllowVoucher(boolean allowVoucher) {
        this.allowVoucher = allowVoucher;
    }

    public boolean isAllowBeanVoucher() {
        return allowBeanVoucher;
    }

    public void setAllowBeanVoucher(boolean allowBeanVoucher) {
        this.allowBeanVoucher = allowBeanVoucher;
    }

    public boolean isHasCp() {
        return hasCp;
    }

    public void setHasCp(boolean hasCp) {
        this.hasCp = hasCp;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public boolean isSerial() {
        return isSerial;
    }

    public void setSerial(boolean serial) {
        isSerial = serial;
    }

    public int getChaptersCount() {
        return chaptersCount;
    }

    public void setChaptersCount(int chaptersCount) {
        this.chaptersCount = chaptersCount;
    }

    public List<String> getGender() {
        return gender;
    }

    public void setGender(List<String> gender) {
        this.gender = gender;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public boolean isDonate() {
        return donate;
    }

    public void setDonate(boolean donate) {
        this.donate = donate;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public boolean is_gg() {
        return _gg;
    }

    public void set_gg(boolean _gg) {
        this._gg = _gg;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public class Rating implements Serializable{
        //              "count": 17432,
//                "score": 8.686,
//                "isEffect": true
        private int count;
        private float score;
        private boolean isEffect;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public float getScore() {
            return score;
        }

        public void setScore(float score) {
            this.score = score;
        }

        public boolean isEffect() {
            return isEffect;
        }

        public void setEffect(boolean effect) {
            isEffect = effect;
        }
    }
}
