package com.geek.rubbish.mvp.module.common.model;

import android.app.Application;

import com.geek.rubbish.app.api.BaseService;
import com.geek.rubbish.mvp.model.BaseArrayData;
import com.geek.rubbish.mvp.model.BaseResponse;
import com.geek.rubbish.mvp.model.bean.RubbishCategoryBean;
import com.geek.rubbish.mvp.module.common.contract.MainContract;
import com.geek.rubbish.storage.entity.ResultBean;
import com.geek.rubbish.storage.entity.UserInfoBean;
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
    public Observable<BaseResponse<BaseArrayData<UserInfoBean>>> memberList(int memberRank) {
        return mRepositoryManager.obtainRetrofitService(BaseService.class).memberList(memberRank);
    }
    @Override
    public Observable<BaseResponse<BaseArrayData<RubbishCategoryBean>>> rubbishCategory() {
        return mRepositoryManager.obtainRetrofitService(BaseService.class).rubbishCategory();
    }
    @Override
    public Observable<BaseResponse<ResultBean>> rubbishRecycleAdd(long memberId, int rubbishCategoryId, int weight, int point) {
        return mRepositoryManager.obtainRetrofitService(BaseService.class).rubbishRecycleAdd(memberId,rubbishCategoryId,weight,point);
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