package com.example.reader.bookcity.bookClassify.detailMVVM;

import com.example.reader.Api.BookApi;
import com.example.reader.bean.BookDetail;
import com.example.reader.module.BookApiModule;

import okhttp3.OkHttpClient;
import rx.Observable;

/**
 * @author weichangsha
 */
public class DetailRepository implements IDetailMvvm {
    private BookApi bookApi;
    private BookApiModule bookApiModule = new BookApiModule();


    @Override
    public Observable<BookDetail> getBookDetail(String bookId) {
        OkHttpClient client = bookApiModule.provideOkHttpClient();
        bookApi = bookApiModule.provideBookService(client);
        return bookApi.getBookDetail(bookId);
    }

    @Override
    public void assembleViewModel(DetailViewModel viewModel, BookDetail bookDetail) {

        viewModel.authorLiveData.postValue(bookDetail.getAuthor());
        viewModel.totalWordsCountLiveData.postValue(bookDetail.getWordCount() + "");
        viewModel.totalChaLiveData.postValue(bookDetail.getChaptersCount() + "");
        viewModel.lastUpdateTimeLivedata.postValue(bookDetail.getUpdated());
        viewModel.infoLivaData.postValue(bookDetail.getLongIntro());
        viewModel.lastUpdateChaLivaData.postValue(bookDetail.getLastChapter());
        viewModel.bookNameLivedata.postValue(bookDetail.getTitle());

    }
}
