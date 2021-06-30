package com.example.reader.bookcity.bookClassify.classifyListMvp;

import com.example.reader.base.BasePresenter;
import com.example.reader.bean.Bookbean;
import com.example.reader.bookcity.util.Utility;

import java.util.ArrayList;
import java.util.List;

public class ClistPresenter extends BasePresenter<ClistModel,CListActivity, IClassifyListContract.P> {
    @Override
    public ClistModel getModelInstance() {
        return new ClistModel(this);
    }

    @Override
    public IClassifyListContract.P getContract() {
        return new IClassifyListContract.P() {
            @Override
            public void requestCList(String id) {
                mModel.getContract().requestCListFromServer(id);
            }

            @Override
            public void responseClist(String responseText) {
                List<Bookbean> bookbeanList = new ArrayList<>();
                Utility.handleBookDataResponse(responseText,bookbeanList);
                mView.getContract().responseCList(bookbeanList);
            }
        };
    }
}
