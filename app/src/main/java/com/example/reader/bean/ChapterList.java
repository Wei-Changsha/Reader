package com.example.reader.bean;

import java.io.Serializable;
import java.util.List;

public class ChapterList {
//    {
//        "mixToc": {
//        "_id": "5569c728e8e4599f7b3a3abd",
//                "book": "5569ba444127a49f1fa99d29",
//                "chaptersCount1": 819,
//                "chaptersUpdated": "2017-06-01T19:36:33.295Z",
//                "chapters": [
//        {
//            "link": "http://book.my716.com/getBooks.aspx?method=content&bookId=633074&chapterFile=U_753547_201607012243065574_6770_1.txt",
//                "title": "第1章 家族黑历史",
//                "unreadble": false
//        }
//        ],
//        "updated": "2017-10-22T21:06:14.019Z"
//    },
//        "ok": true
//    }
    private boolean ok;
    private MixToc mixToc;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public MixToc getMixToc() {
        return mixToc;
    }

    public void setMixToc(MixToc mixToc) {
        this.mixToc = mixToc;
    }

    public class MixToc{

        private String _id;
        private String book;
        private int chaptersCount1;
        private String chaptersUpdated;
        private String updated;
        private List<Chapters> chapters;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getBook() {
            return book;
        }

        public void setBook(String book) {
            this.book = book;
        }

        public int getChaptersCount1() {
            return chaptersCount1;
        }

        public void setChaptersCount1(int chaptersCount1) {
            this.chaptersCount1 = chaptersCount1;
        }

        public String getChaptersUpdated() {
            return chaptersUpdated;
        }

        public void setChaptersUpdated(String chaptersUpdated) {
            this.chaptersUpdated = chaptersUpdated;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public List<Chapters> getChapters() {
            return chapters;
        }

        public void setChapters(List<Chapters> chapters) {
            this.chapters = chapters;
        }

        public class Chapters implements Serializable {
            private String link;
            private String title;
            private boolean unreadble;

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public boolean isUnreadble() {
                return unreadble;
            }

            public void setUnreadble(boolean unreadble) {
                this.unreadble = unreadble;
            }
        }
    }

}
