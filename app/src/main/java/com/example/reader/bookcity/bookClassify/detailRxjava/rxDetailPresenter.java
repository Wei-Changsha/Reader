package com.example.reader.bookcity.bookClassify.detailRxjava;

import android.util.Log;

import com.example.reader.Api.BookApi;
import com.example.reader.base.BasePresenter;
import com.example.reader.bean.BookDetail;
import com.example.reader.bookcity.bookClassify.detailMvp.MVPDetailModel;
import com.example.reader.module.BookApiModule;

import okhttp3.OkHttpClient;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author wei
 */
public class rxDetailPresenter extends BasePresenter<MVPDetailModel, rxDetailActivity, IdetailContractRx.P> {

    private BookApi bookApi;
    BookApiModule bookApiModule = new BookApiModule();

    public rxDetailPresenter(){}

    public rxDetailPresenter(BookApi bookApi) {
        this.bookApi = bookApi;
    }

    @Override
    public MVPDetailModel getModelInstance() {
        //return new MVPDetailModel(this);
        return null;
    }

    @Override
    public IdetailContractRx.P getContract() {
        return new IdetailContractRx.P() {
            @Override
            public void getBookDetail(String bookId) {

                Log.d("rxDetailPresenter rrrr 000 bookid1 = ", bookId);
                OkHttpClient client = bookApiModule.provideOkHttpClient();
                bookApi = bookApiModule.provideBookService(client);

                Subscription subscription = bookApi.getBookDetail(bookId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<BookDetail>() {
                            @Override
                            public void onCompleted() {
                                //Log.d("oooo complete11","rxDetail- p");
                            }

                            @Override
                            public void onError(Throwable e) {
                                //Log.d("0000 eeerror11","rxDetail- p");
                            }

                            @Override
                            public void onNext(BookDetail bookDetail) {
                                //在这里直接返回网络请求的数据，可以不用谢Model层
                                Log.d("0000 next","rxDetail- p");
                                mView.getContract().showBookDetail(bookDetail);
                            }
                        });
                //addSubscribe(subscription);
            }
        };
    }

}
