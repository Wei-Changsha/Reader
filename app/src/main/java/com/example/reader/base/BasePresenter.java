package com.example.reader.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<M extends BaseModel, V extends BaseActivity,CONTRACT> extends SuperBase<CONTRACT>{
    public M mModel;
    public V mView;
    public BasePresenter(){
        mModel = getModelInstance();
    }

    public void bindView(V mView){
        this.mView = mView;
    }

    public void unBindView() {
        this.mView = null;
    }

    public abstract M getModelInstance();

    protected CompositeSubscription mCompositeSubscription;

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }
}
