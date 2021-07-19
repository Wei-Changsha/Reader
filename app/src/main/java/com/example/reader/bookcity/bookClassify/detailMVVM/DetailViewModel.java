package com.example.reader.bookcity.bookClassify.detailMVVM;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.reader.bean.BookDetail;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author weichangsha
 */
public class DetailViewModel extends AndroidViewModel {
    private  DetailRepository repository;

    public DetailRepository getRepository() {
        return repository;
    }

    public void setRepository(DetailRepository repository) {
        this.repository = repository;
    }

    public final MutableLiveData<String> bookNameLivedata = new MutableLiveData<>();
    public final MutableLiveData<String> authorLiveData = new MutableLiveData<>();
    public final MutableLiveData<String> lastUpdateChaLivaData = new MutableLiveData<>();
    public final MutableLiveData<String> totalChaLiveData = new MutableLiveData<>();
    public final MutableLiveData<String> totalWordsCountLiveData = new MutableLiveData<>();
    public final MutableLiveData<String> lastUpdateTimeLivedata = new MutableLiveData<>();
    public final MutableLiveData<String> infoLivaData = new MutableLiveData<>();

    public DetailViewModel(Application application, DetailRepository repository) {
        super(application);
        this.repository = repository;
    }
    public DetailViewModel(Application application) {
        super(application);
    }

    public void getBookDetailById(String bookId) {
        //TODO  调用Repository的函数来实现获取数据
        repository.getBookDetail(bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BookDetail bookDetail) {
                        repository.assembleViewModel(DetailViewModel.this, bookDetail);

                    }
                });
    }
}
