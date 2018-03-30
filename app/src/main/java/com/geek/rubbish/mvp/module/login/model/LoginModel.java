package com.geek.rubbish.mvp.module.login.model;

import android.app.Application;

import com.geek.rubbish.app.api.BaseApi;
import com.geek.rubbish.storage.BaseResponse;
import com.geek.rubbish.storage.entity.UserBean;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.geek.rubbish.mvp.module.login.contract.LoginContract;

import io.reactivex.Observable;


@ActivityScope
public class LoginModel extends BaseModel implements LoginContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public LoginModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseResponse<UserBean>> login(String mobile, String md5Password) {
        return mRepositoryManager.obtainRetrofitService(BaseApi.class).login(mobile, md5Password);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}