package com.example.reader.bookcity.bookClassify.classifyListMvp;

import com.example.reader.base.BaseModel;
import com.example.reader.bookcity.util.HttpUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author wei
 */
public class ClistModel extends BaseModel<ClistPresenter, IClassifyListContract.M> {
    public ClistModel(ClistPresenter mPresenter) {
        super(mPresenter);
    }

    @Override
    public IClassifyListContract.M getContract() {
        return new IClassifyListContract.M() {
            @Override
            public void requestCListFromServer(String id) {
                HttpUtil.sendOkHttpRequest(id, new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull final IOException e) {

                    }
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String responseText=response.body().string();
                        mPresenter.getContract().responseClist(responseText);
                    }

                });
            }
        };
    }
}
