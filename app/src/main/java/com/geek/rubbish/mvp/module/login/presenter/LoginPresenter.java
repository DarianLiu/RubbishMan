package com.geek.rubbish.mvp.module.login.presenter;

import android.app.Application;
import android.util.Log;

import com.geek.rubbish.common.utils.RxUtil;
import com.geek.rubbish.mvp.module.login.contract.LoginContract;
import com.geek.rubbish.storage.entity.UserBean;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    private static final String TAG = "LoginPresenter";
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView) {
        super(model, rootView);
    }

    /**
     * 登录
     *
     * @param account     用户名、手机号
     * @param md5Password 密码
     */
    public void login(String account, String md5Password) {
        Log.e(TAG, "====== account : " + account + "  md5Password: " + md5Password);
        mModel.login(account, md5Password)
                .retryWhen(new RetryWithDelay(0, 30))
                .compose(RxUtil.applySchedulers(mRootView))
                .compose(RxUtil.handleBaseResult(mAppManager.getTopActivity()))
                .subscribeWith(new ErrorHandleSubscriber<UserBean>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull UserBean userBean) {
//                        DataHelper.setStringSF(mAppManager.getTopActivity(), Constants.SP_TOKEN, userBean.getToken());
//                        DataHelper.saveDeviceData(mAppManager.getTopActivity(), Constants.SP_USER_INFO, userBean);
                        Log.e(TAG, "====== onNext id: ");
                        //Log.e(TAG, "====== onNext id: "+userBean.getId());
                    }

                    @Override
                    public void onError(@NonNull Throwable t) {
                        super.onError(t);
                        Log.e("TAG", "====== onError");
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
