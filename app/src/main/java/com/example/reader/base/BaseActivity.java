package com.example.reader.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<P extends  BasePresenter,CONTRACT> extends AppCompatActivity {

    public P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        mPresenter = getPresenterInstance();//只获得实例就可以了
        mPresenter.bindView(this);
        findView();//findView必须要放在这个后面，因为要先获取Presenter实例才能够使用它
        //initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unBindView();
    }

    public abstract void findView();
    public abstract int getContentViewId();
    //public abstract void initView();

    public abstract P getPresenterInstance();
    public abstract CONTRACT getContract();



}
