package com.geek.rubbish.mvp.module.login.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.geek.rubbish.common.utils.StringUtils;
import com.geek.rubbish.common.widget.dialog.CircleProgressDialog;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.geek.rubbish.mvp.module.login.di.component.DaggerLoginComponent;
import com.geek.rubbish.mvp.module.login.di.module.LoginModule;
import com.geek.rubbish.mvp.module.login.contract.LoginContract;
import com.geek.rubbish.mvp.module.login.presenter.LoginPresenter;

import com.geek.rubbish.R;


import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

import static com.geek.rubbish.R.id.tv_toolbar_title;
import static com.jess.arms.utils.Preconditions.checkNotNull;


public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.et_account)
    TextInputEditText etAccount;
    @BindView(R.id.til_account)
    TextInputLayout tilAccount;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    @BindString(R.string.error_account_null)
    String error_account_null;
    @BindString(R.string.error_password_null)
    String error_password_null;

    private CircleProgressDialog loadingDialog;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        tvToolbarTitle.setText("登录");
    }

    @OnClick({R.id.btn_login})
    public void onViewClicked(View view) {
        String account = etAccount.getText().toString();
        String password = etPassword.getText().toString();
        switch (view.getId()) {
            case R.id.btn_login:
                if (validateAccount(account) && validatePassword(password)) {
                    mPresenter.login(StringUtils.stringUTF8(account), ArmsUtils.encodeToMD5(password));
                }
                break;
//            case R.id.tv_register:
//                launchActivity(new Intent(LoginActivity.this, CaptchaActivity.class));
//                break;
        }
    }

    /**
     * 验证密码
     *
     * @param password 密码
     */
    private boolean validatePassword(String password) {
        if (TextUtils.isEmpty(password)) {
            showError(tilPassword, error_password_null);
            return false;
        }
        tilPassword.setErrorEnabled(false);
        return true;
    }

    /**
     * 验证账号
     *
     * @param account 用户名或手机号码
     */
    private boolean validateAccount(String account) {
        if (TextUtils.isEmpty(account)) {
            showError(tilAccount, error_account_null);
            return false;
        }
        tilAccount.setErrorEnabled(false);
        return true;
    }

    /**
     * 显示错误提示
     *
     * @param textInputLayout 对应控件
     * @param error           错误信息
     */
    private void showError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
    }


    @Override
    public void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = new CircleProgressDialog.Builder(this).create();
        }
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
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
