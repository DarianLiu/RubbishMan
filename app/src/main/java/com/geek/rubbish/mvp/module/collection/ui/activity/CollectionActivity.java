package com.geek.rubbish.mvp.module.collection.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.geek.rubbish.mvp.module.collection.di.component.DaggerCollectionComponent;
import com.geek.rubbish.mvp.module.collection.di.module.CollectionModule;
import com.geek.rubbish.mvp.module.collection.contract.CollectionContract;
import com.geek.rubbish.mvp.module.collection.presenter.CollectionPresenter;

import com.geek.rubbish.R;


import static com.jess.arms.utils.Preconditions.checkNotNull;


public class CollectionActivity extends BaseActivity<CollectionPresenter> implements CollectionContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCollectionComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .collectionModule(new CollectionModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_collection; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }
}
