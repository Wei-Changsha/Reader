package com.example.reader.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

public class BookD extends DataSupport implements Serializable {
    private String tag;//用来装body
    private String link;
    private String title;
    private String body;

    public BookD(String tag, String link, String title,String body) {
        this.tag = tag;
        this.link = link;
        this.title = title;
        this.body=body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

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
}
