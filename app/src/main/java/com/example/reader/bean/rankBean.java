package com.example.reader.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class rankBean {
//    {
//        "ranking": {
//        "_id": "54d43437d47d13ff21cad58b",
//                "updated": "2017-09-10T21:20:06.740Z",
//                "title": "追书最热榜 Top100",
//                "tag": "manualRank",
//                "cover": "/ranking-cover/142319314350435",
//                "icon": "/cover/148945782817557",
//                "__v": 1194,
//                "monthRank": "564d853484665f97662d0810",
//                "totalRank": "564d85b6dd2bd1ec660ea8e2",
//                "shortTitle": "最热榜",
//                "created": "2017-09-11T13:20:35.079Z",
//                "isSub": false,
//                "collapse": false,
//                "new": true,
//                "gender": "female",
//                "priority": 250,
//                "books": [
//        {
//            "_id": "578f718209013c307e2c71c0",
//                "title": "隐婚100分：惹火娇妻嫁一送一",
//                "author": "囧囧有妖",
//                "shortIntro": "“你救了我，我让我爹地以身相许！”宁夕意外救了只小包子，结果被附赠了一只大包子。婚后，陆霆骁宠妻如命千依百顺，虐起狗来连亲儿子都不放过。“老板，公司真给夫人拿去玩？难道夫人要卖公司您也不管？”“卖你家公司了？”“大少爷，不好了！夫人说要把屋顶掀了！”“还不去帮夫人扶梯子。”“粑粑，谢谢你给小宝买的大熊！”“那是买给你妈妈的。”“老公，这个剧本我特别喜欢，只是床戏有点多，我可以接吗？”陆霆骁神色淡定：“可以。”当天晚上，宁夕扶着腰连滚带爬逃下床。陆霆骁！可以你大爷！！！",
//                "cover": "/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F86590%2F86590_9ec02fe59dbd40b999c420fcc43a75b0.jpg%2F",
//                "site": "zhuishuvip",
//                "banned": 0,
//                "latelyFollower": 82377,
//                "retentionRatio": "45.82"
//        }...
//        ],
//        "id": "54d43437d47d13ff21cad58b",
//                "total": 98
//    },
//        "ok": true
//    }
    private Ranking ranking;
    private boolean ok;

    public Ranking getRanking() {
        return ranking;
    }

    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public static class Ranking{
        private String _id;
        private String updated;
        private String title;
        private String tag;
        private String cover;
        private String icon;
        private int __v;
        private String monthRank;
        private String totalRank;
        private String shortTitle;
        private String created;
        private boolean isSub;
        private boolean collapse;
        @SerializedName("new")
        private boolean _new;
        private String gender;
        private int priority;
        private List<RankBooks> books;
        private String id;
        private int total;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }

        public String getMonthRank() {
            return monthRank;
        }

        public void setMonthRank(String monthRank) {
            this.monthRank = monthRank;
        }

        public String getTotalRank() {
            return totalRank;
        }

        public void setTotalRank(String totalRank) {
            this.totalRank = totalRank;
        }

        public String getShortTitle() {
            return shortTitle;
        }

        public void setShortTitle(String shortTitle) {
            this.shortTitle = shortTitle;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public boolean isSub() {
            return isSub;
        }

        public void setSub(boolean sub) {
            isSub = sub;
        }

        public boolean isCollapse() {
            return collapse;
        }

        public void setCollapse(boolean collapse) {
            this.collapse = collapse;
        }

        public boolean is_new() {
            return _new;
        }

        public void set_new(boolean _new) {
            this._new = _new;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public List<RankBooks> getBooks() {
            return books;
        }

        public void setBooks(List<RankBooks> books) {
            this.books = books;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public static class RankBooks{
            private String _id;//"5816b415b06d1d32157790b1",
            private String title;//"圣墟",
            private String author;//"辰东",
            private String shortIntro;// "在破败中崛起，在寂灭中复苏。沧海成尘，雷电枯竭，那一缕幽雾又一次临近大地，世间的枷锁被打开了，一个全新的世界就此揭开神秘的一角……",
            private String cover;//"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F1228859%2F_1228859_441552.jpg%2F",
            private String site;//"zhuishuvip",
            private int banned;
            private int latelyFollower;
            private String retentionRatio;

            public RankBooks(String _id, String title, String author, String shortIntro, String cover,
                             String site, int banned, int latelyFollower, String retentionRatio) {
                this._id = _id;
                this.title = title;
                this.author = author;
                this.shortIntro = shortIntro;
                this.cover = cover;
                this.site = site;
                this.banned = banned;
                this.latelyFollower = latelyFollower;
                this.retentionRatio = retentionRatio;
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

            public int getBanned() {
                return banned;
            }

            public void setBanned(int banned) {
                this.banned = banned;
            }

            public int getLatelyFollower() {
                return latelyFollower;
            }

            public void setLatelyFollower(int latelyFollower) {
                this.latelyFollower = latelyFollower;
            }

            public String getRetentionRatio() {
                return retentionRatio;
            }

            public void setRetentionRatio(String retentionRatio) {
                this.retentionRatio = retentionRatio;
            }
        }




    }
}
