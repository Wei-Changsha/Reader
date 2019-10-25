package com.example.reader.bookcity.bookListAvtivity;

import java.util.List;

public class ByList {
//    {
//        "total": 1916,
//            "bookLists": [
//        {
//            "_id": "5659f9c28498cf236a508b08",
//                "title": "无敌爽文🐎",
//                "author": "原罪&七罪",
//                "desc": "本人很,懒喜欢的收藏  经典📚无限📘  粮草&储备   ♞♞ 挑灯💡夜读📖   🌙",
//                "gender": "male",
//                "collectorCount": 31459,
//                "cover": "/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F1183975%2F_1183975_531140.jpg%2F",
//                "bookCount": 437
//        }...
//    ],
//        "ok": true
//    }


    private String total;
    private List<Book> bookList;
    private String ok;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public static class Book{
        private String _id;
        private String title;
        private String author;
        private String desc;
        private String gender;
        private String collectorCount;
        private String cover;
        private String bookCount;
        private List<String> covers;

        public Book(String _id, String title, String author, String desc, String gender,
                    String collectorCount, String cover, String bookCount) {
            this._id = _id;
            this.title = title;
            this.author = author;
            this.desc = desc;
            this.gender = gender;
            this.collectorCount = collectorCount;
            this.cover = cover;
            this.bookCount = bookCount;
        }

        public List<String> getCovers() {
            return covers;
        }

        public void setCovers(List<String> covers) {
            this.covers = covers;
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

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getCollectorCount() {
            return collectorCount;
        }

        public void setCollectorCount(String collectorCount) {
            this.collectorCount = collectorCount;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getBookCount() {
            return bookCount;
        }

        public void setBookCount(String bookCount) {
            this.bookCount = bookCount;
        }
    }

}
