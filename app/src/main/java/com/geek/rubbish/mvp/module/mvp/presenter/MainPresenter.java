package com.geek.rubbish.mvp.module.mvp.presenter;

import com.geek.rubbish.app.api.RxUtils;
import com.geek.rubbish.mvp.model.BaseArrayData;
import com.geek.rubbish.mvp.model.bean.RubbishCategoryBean;
import com.geek.rubbish.mvp.module.mvp.contract.MainContract;
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
     * 垃圾分类列表
     */
    public void rubbishCategory() {
        mModel.rubbishCategory().retryWhen(new RetryWithDelay(3, 2))
                .compose(RxUtils.applySchedulers(mRootView))
                .compose(RxUtils.handleBaseResult(mAppManager.getTopActivity()))
                .subscribeWith(new ErrorHandleSubscriber<BaseArrayData<RubbishCategoryBean>>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull BaseArrayData<RubbishCategoryBean> arrayData) {
                        mRootView.updateRubbishCategoryView(arrayData.getPageData());
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
