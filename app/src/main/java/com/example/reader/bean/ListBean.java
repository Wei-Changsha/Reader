package com.example.reader.bean;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;


public class ListBean implements Serializable {
    //    {"ok":true,
//            "bookList":
//        {
//            "id":"5659f9c28498cf236a508b08",
//             "_id":"5659f9c28498cf236a508b08",
//             "author":
//            {
//                 "_id":"54f218a39a845dc479c6a812",
//                 "avatar":"/avatar/cd/c3/cdc337738cd42ff60705f707083335f7",
//                 "lv":12,
//                 "nickname":"??食書鬼??",
//                 "type":"normal",
//                 "gender":null
//                 },
//              "title":"都市?YY无敌爽文??粮草",
//              "desc":"懒喜欢的收藏  经典??无限??  粮草&储备  挑灯??夜读??   ??",
//              "gender":"male",
//              "updateCount":145,
//              "created":"2015-11-28T19:00:18.138Z",
//              "updated":"2019-05-05T18:53:57.651Z",
//              "tags":["COLLECT_COUNT_X000+","热血"],
//              "stickStopTime":"2015-11-28T19:00:18.000Z",
//              "isDraft":false,
//              "isDistillate":null,
//              "collectorCount":44421,
//              "shareLink":"http://share.zhuishushenqi.com/booklist/5659f9c28498cf236a508b08",
//              "total":496,
//              "books":[

//                {"book":
//                {"cat":"异界大陆",
//                        "_id":"5290cd60e9ec37c06802c8de",
//                        "title":"火影之天之眼",
//                        "author":"晕血的羔羊",
//                        "longIntro":"",
//                        "cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F609673%2F_609673_422776.jpg%2F",
//                        "site":"zhuishuvip",
//                        "majorCate":"玄幻",
//                        "minorCate":"异界大陆",
//                        "allowMonthly":true,
//                        "allowFree":true,
//                        "banned":0,
//                        "latelyFollower":434,
//                        "wordCount":878231,
//                        "retentionRatio":31.409999847412109},
//                "comment":""
//            }
//                    ]
//        }
//    }


    private  BookList bookList;
    private boolean ok;

    public BookList getBookList() {
        return bookList;
    }

    public void setBookList(BookList bookList) {
        this.bookList = bookList;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public class BookList{

        private String _id;
        private String id;
        private Author author;
        private String title;
        private String desc;
        private String gender;
        private int  updateCount;
        private String created;
        private String updated;
        private List<String> tags;
        private String stickStopTime;
        private boolean isDraft;
        @Nullable
        private boolean isDistillate;
        private int collectorCount;
        private String shareLink;
        private int total;
        private List<Books> books;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public int getUpdateCount() {
            return updateCount;
        }

        public void setUpdateCount(int updateCount) {
            this.updateCount = updateCount;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public String getStickStopTime() {
            return stickStopTime;
        }

        public void setStickStopTime(String stickStopTime) {
            this.stickStopTime = stickStopTime;
        }

        public boolean isDraft() {
            return isDraft;
        }

        public void setDraft(boolean draft) {
            isDraft = draft;
        }

        @Nullable
        public boolean isDistillate() {
            return isDistillate;
        }

        public void setDistillate(boolean distillate) {
            isDistillate = distillate;
        }

        public int getCollectorCount() {
            return collectorCount;
        }

        public void setCollectorCount(int collectorCount) {
            this.collectorCount = collectorCount;
        }

        public String getShareLink() {
            return shareLink;
        }

        public void setShareLink(String shareLink) {
            this.shareLink = shareLink;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<Books> getBooks() {
            return books;
        }

        public void setBooks(List<Books> books) {
            this.books = books;
        }

        public class Books{
            private String comment;
            private Book book;

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public Book getBook() {
                return book;
            }

            public void setBook(Book book) {
                this.book = book;
            }

            public class Book{
                //                        "cat":"异界大陆",
//                        "_id":"5290cd60e9ec37c06802c8de",
//                        "title":"火影之天之眼",
//                        "author":"晕血的羔羊",
//                        "longIntro":"",
//                        "cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F609673%2F_609673_422776.jpg%2F",
//                        "site":"zhuishuvip",
//                        "majorCate":"玄幻",
//                        "minorCate":"异界大陆",
//                        "allowMonthly":true,
//                        "allowFree":true,
//                        "banned":0,
//                        "latelyFollower":434,
//                        "wordCount":878231,
//                        "retentionRatio":31.409999847412109
                private String cat;
                private String _id;
                private String title;
                private String author;
                private String longIntro;
                private String cover;
                private String site;
                private String majorCate;
                private String minorCate;
                private boolean allowMonthly;
                private boolean allowFree;
                private int banned;
                private int latelyFollower;
                private int wordCount;
                private int retentionRatio;

                public String getCat() {
                    return cat;
                }

                public void setCat(String cat) {
                    this.cat = cat;
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

                public String getLongIntro() {
                    return longIntro;
                }

                public void setLongIntro(String longIntro) {
                    this.longIntro = longIntro;
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

                public boolean isAllowMonthly() {
                    return allowMonthly;
                }

                public void setAllowMonthly(boolean allowMonthly) {
                    this.allowMonthly = allowMonthly;
                }

                public boolean isAllowFree() {
                    return allowFree;
                }

                public void setAllowFree(boolean allowFree) {
                    this.allowFree = allowFree;
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

                public int getWordCount() {
                    return wordCount;
                }

                public void setWordCount(int wordCount) {
                    this.wordCount = wordCount;
                }

                public int getRetentionRatio() {
                    return retentionRatio;
                }

                public void setRetentionRatio(int retentionRatio) {
                    this.retentionRatio = retentionRatio;
                }
            }
        }


        public class Author{
            //           "_id":"54f218a39a845dc479c6a812",
//                 "avatar":"/avatar/cd/c3/cdc337738cd42ff60705f707083335f7",
//                 "lv":12,
//                 "nickname":"??食書鬼??",
//                 "type":"normal",
//                 "gender":null

            private String _id;
            private String avatar;
            private String lv;
            private String nickname;
            private String type;
            @Nullable
            private String gender;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getLv() {
                return lv;
            }

            public void setLv(String lv) {
                this.lv = lv;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            @Nullable
            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }
        }


    }
}
