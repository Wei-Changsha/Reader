package com.example.reader.bookcity.bookClassify.detailMVVM;

import com.example.reader.bean.BookDetail;

import rx.Observable;

/**
 * @author weichangsha
 */
public interface IDetailMvvm {

    Observable<BookDetail> getBookDetail(String bookId);//在P层调用，P层向M层发起请求

    void assembleViewModel(DetailViewModel viewModel, BookDetail bookDetail);
}
