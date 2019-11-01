package com.example.reader.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

public class Book extends DataSupport implements Serializable {
    private String Tag;//用来保存ID
    private String Title;
    private String Author;//last chapter
    private String link;
    private String cover;

    public Book(String tag, String title, String author, String link, String cover) {
        Tag = tag;
        Title = title;
        Author = author;
        this.link = link;
        this.cover = cover;
    }
    public Book(){

    }



    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
