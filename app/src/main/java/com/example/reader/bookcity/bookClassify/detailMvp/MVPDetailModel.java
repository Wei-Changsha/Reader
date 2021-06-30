package com.example.reader.bookcity.bookClassify.detailMvp;

import com.example.reader.base.BaseModel;
import com.example.reader.bookcity.util.HttpUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MVPDetailModel extends BaseModel<MVPDetailPresenter,IDetailContract.M> {
    public MVPDetailModel(MVPDetailPresenter mPresenter) {
        super(mPresenter);
    }

    @Override
    public IDetailContract.M getContract() {
        return new IDetailContract.M() {
            @Override
            public void requestBookDetailFromServer(String bookId) {

                //这里应该开一个新线程来访问网
                HttpUtil.sendOkHttpRequest(bookId, new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String responseText = response.body().string();
                        mPresenter.getContract().responseBookDetail(responseText);
                    }
                });
            }
        };
    }
}
