package com.geek.rubbish.mvp.module.common.presenter;

import com.geek.rubbish.app.api.RxUtils;
import com.geek.rubbish.mvp.model.BaseArrayData;
import com.geek.rubbish.mvp.model.bean.RubbishCategoryBean;
import com.geek.rubbish.mvp.module.common.contract.MainContract;
import com.geek.rubbish.storage.entity.ResultBean;
import com.geek.rubbish.storage.entity.UserInfoBean;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


@ActivityScope
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    AppManager mAppManager;

    @Inject
    public MainPresenter(MainContract.Model model, MainContract.View rootView) {
        super(model, rootView);
    }

    /**
     * 获取居民用户列表
     */
    public void memberList(int memberRank) {
        mModel.memberList(memberRank).retryWhen(new RetryWithDelay(0, 30))
                .compose(RxUtils.applySchedulers(mRootView))
                .compose(RxUtils.handleBaseResult(mAppManager.getTopActivity()))
                .subscribeWith(new ErrorHandleSubscriber<BaseArrayData<UserInfoBean>>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull BaseArrayData<UserInfoBean> arrayData) {
                        mRootView.updateMemberView(arrayData.getPageData());
                    }
                });
    }

    /**
     * 垃圾分类列表
     */
    public void rubbishCategory() {
        mModel.rubbishCategory().retryWhen(new RetryWithDelay(0, 30))
                .compose(RxUtils.applySchedulers(mRootView))
                .compose(RxUtils.handleBaseResult(mAppManager.getTopActivity()))
                .subscribeWith(new ErrorHandleSubscriber<BaseArrayData<RubbishCategoryBean>>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull BaseArrayData<RubbishCategoryBean> arrayData) {
                        mRootView.updateRubbishCategoryView(arrayData.getPageData());
                    }
                });
    }

    /**
     * 保存垃圾回收信息
     */
    public void rubbishRecycleAdd(long memberId, int rubbishCategoryId, int weight, int point) {
        mModel.rubbishRecycleAdd(memberId,rubbishCategoryId,weight,point).retryWhen(new RetryWithDelay(0, 30))
                .compose(RxUtils.applySchedulers(mRootView))
                .compose(RxUtils.handleBaseResult(mAppManager.getTopActivity()))
                .subscribeWith(new ErrorHandleSubscriber<ResultBean>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull ResultBean r) {
//                        if (r==1) {
                            mRootView.showInfo("保存成功！");
//                        } else {
//                            mRootView.showInfo("保存失败，请重试！");
//                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
    }

}
