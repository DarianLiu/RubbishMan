package com.geek.rubbish.mvp.module.mvp.model;

import android.app.Application;

import com.geek.rubbish.app.api.BaseService;
import com.geek.rubbish.mvp.model.BaseArrayData;
import com.geek.rubbish.mvp.model.BaseResponse;
import com.geek.rubbish.mvp.model.bean.RubbishCategoryBean;
import com.geek.rubbish.mvp.module.mvp.contract.MainContract;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class MainModel extends BaseModel implements MainContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;
    @Override
    public Observable<BaseResponse<BaseArrayData<RubbishCategoryBean>>> rubbishCategory() {
        return mRepositoryManager.obtainRetrofitService(BaseService.class).rubbishCategory();
    }

    @Inject
    public MainModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}