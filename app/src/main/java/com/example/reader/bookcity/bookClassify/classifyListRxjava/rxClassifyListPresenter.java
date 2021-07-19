package com.example.reader.bookcity.bookClassify.classifyListRxjava;

import android.util.Log;

import com.example.reader.Api.BookApi;
import com.example.reader.base.BasePresenter;
import com.example.reader.bean.Bookbean;
import com.example.reader.bookcity.bookClassify.classifyListMvp.ClistModel;
import com.example.reader.data.ClassifyListBody;
import com.example.reader.module.BookApiModule;

import java.util.List;

import okhttp3.OkHttpClient;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author wei
 * @date 2021.06.30
 */
public class rxClassifyListPresenter extends BasePresenter<ClistModel, rxClassifyListActivity, IClassifyListContractRx.P> {

    BookApiModule bookApiModule = new BookApiModule();


    rxClassifyListPresenter() {};

    @Override
    public ClistModel getModelInstance() {
        return null;
    }

    @Override
    public IClassifyListContractRx.P getContract() {
        return new IClassifyListContractRx.P() {
            @Override
            public void getClassifyListDetail(String gender,String type, String major, String minor, int start, int limit) {

                OkHttpClient client = bookApiModule.provideOkHttpClient();
                BookApi bookApi = bookApiModule.provideBookService(client);
                Subscription subscription = bookApi.getClassifyList(gender, type, major, minor, start, limit)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ClassifyListBody>() {
                            @Override
                            public void onCompleted() {
                                Log.d("vvvv complete", "rxClassifyListPresenter ");
                            }

                            @Override
                            public void onError(Throwable e) {

                                Log.d("vvvv error", e.getMessage());
                            }

                            @Override
                            public void onNext(ClassifyListBody body) {
                                Log.d("vvvv next", "rxClassifyListPresenter ");
                                List<Bookbean> books = body.getBooks();
                                mView.getContract().showClassifyList(books);
                            }
                        });
            }
        };
    }
}
