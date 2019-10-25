package com.example.reader.bookcity.bookRanking;

import java.util.List;

public class ByRanking {
//    {
//        "male": [
//        {
//            "_id": "54d42d92321052167dfb75e3",
//                "title": "追书最热榜 Top100",
//                "cover": "/ranking-cover/142319144267827",
//                "collapse": false,
//                "monthRank": "564d820bc319238a644fb408",
//                "totalRank": "564d8494fe996c25652644d2",
//                "shortTitle": "最热榜"
//        }...
//    ],
//        "female": [
//        {
//            "_id": "54d43437d47d13ff21cad58b",
//                "title": "追书最热榜 Top100",
//                "cover": "/ranking-cover/142319314350435",
//                "collapse": false,
//                "monthRank": "564d853484665f97662d0810",
//                "totalRank": "564d85b6dd2bd1ec660ea8e2",
//                "shortTitle": "最热榜"
//        }...
//    ]
//    }
    private List<Male> male;
    private List<Female> female;

    public List<Male> getMale() {
        return male;
    }

    public void setMale(List<Male> male) {
        this.male = male;
    }

    public List<Female> getFemale() {
        return female;
    }

    public void setFemale(List<Female> female) {
        this.female = female;
    }

    public class Male{
        private String _id;
        private String cover;
        private String title;
        private boolean collapse;
        private String monthRank;
        private String totalRank;
        private String shortTitle;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isCollapse() {
            return collapse;
        }

        public void setCollapse(boolean collapse) {
            this.collapse = collapse;
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
    }

    public class Female{
        private String _id;
        private String cover;
        private String title;
        private boolean collapse;
        private String monthRank;
        private String totalRank;
        private String shortTitle;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isCollapse() {
            return collapse;
        }

        public void setCollapse(boolean collapse) {
            this.collapse = collapse;
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
    }
}
