package com.example.reader.bookcity.bookClassify;

import com.example.reader.bean.Bookbean;

import java.util.List;

/**
 * @author wei
 */
public class ByCategories {
    private String total;
    private List<Bookbean> bookbeans;
    private boolean ok;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


    public List<Bookbean> getBookbeans() {
        return bookbeans;
    }

    public void setBookbeans(List<Bookbean> bookbeans) {
        this.bookbeans = bookbeans;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    @Override
    public String toString() {
        return "ByCategories [total=" + total +
                ", bookbeans=" + bookbeans +
                ",ok=" + ok + "]";
    }

}
