package com.example.reader.data;

import com.example.reader.bean.Bookbean;

import java.util.List;

/**
 * @author wei
 */
public class ClassifyListBody {
    private int total;
    private List<Bookbean> books;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Bookbean> getBooks() {
        return books;
    }

    public void setBooks(List<Bookbean> books) {
        this.books = books;
    }
}
