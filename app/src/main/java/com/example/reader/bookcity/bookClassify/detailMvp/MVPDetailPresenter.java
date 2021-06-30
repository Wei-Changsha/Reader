package com.example.reader.bookcity.bookClassify.detailMvp;

import com.example.reader.Api.BookApi;
import com.example.reader.base.BasePresenter;
import com.example.reader.bean.BookDetail;
import com.example.reader.bookcity.util.Utility;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MVPDetailPresenter extends BasePresenter<MVPDetailModel,MVPDetailActivity,IDetailContract.P> {

    private BookApi bookApi;
    public MVPDetailPresenter(){}
    @Inject
    public MVPDetailPresenter(BookApi bookApi) {
        this.bookApi = bookApi;
    }

    public void getBookDetail(String bookId){
        //这里用的是rxjava1

        Subscription subscription = bookApi.getBookDetail(bookId).subscribeOn(Schedulers.io())
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
                        //在这里直接返回网络请求的数据，可以不用谢Model层
                        mView.getContract().responseBookDetail(bookDetail);
                    }
                });
        //addSubscrebe(rxSubscription);

    }

    @Override
    public MVPDetailModel getModelInstance() {
        return new MVPDetailModel(this);
    }

    @Override
    public IDetailContract.P getContract() {
        return new IDetailContract.P() {
            @Override
            public void requestBookDetail(String bookId) {

                mModel.getContract().requestBookDetailFromServer(bookId);

            }

            @Override
            public void responseBookDetail(String responseText) {
                BookDetail bookDetail = Utility.handleBookDetailResponse(responseText);
                mView.getContract().responseBookDetail(bookDetail);
            }
        };
    }
}
