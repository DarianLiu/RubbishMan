package com.geek.rubbish.mvp.module.common.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.geek.rubbish.R;
import com.geek.rubbish.common.T;
import com.geek.rubbish.common.utils.StringUtils;
import com.geek.rubbish.common.widget.dialog.CircleProgressDialog;
import com.geek.rubbish.mvp.model.bean.RubbishCategoryBean;
import com.geek.rubbish.mvp.module.common.di.component.DaggerMainComponent;
import com.geek.rubbish.mvp.module.common.di.module.MainModule;
import com.geek.rubbish.mvp.module.common.contract.MainContract;
import com.geek.rubbish.mvp.module.common.presenter.MainPresenter;
import com.geek.rubbish.storage.entity.UserInfoBean;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.text.MessageFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spinner_user)
    AppCompatSpinner spinnerUser;
    @BindView(R.id.spinner_category)
    AppCompatSpinner spinnerCategory;
    @BindView(R.id.tv_point)
    TextView tvPoint;
    @BindView(R.id.et_weight)
    AppCompatEditText etWeight;
    @BindView(R.id.btn_save)
    Button btnSave;

    private CircleProgressDialog loadingDialog;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvToolbarTitle.setText("垃圾回收");
        mPresenter.memberList(5);
        mPresenter.rubbishCategory();
        etWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               String value = s.toString();
               tvPoint.setText(MessageFormat.format("获取积分： +{0}", point*Integer.valueOf(value)));
            }
        });
    }

    long memberId = 0;
    @Override
    public void updateMemberView(List<UserInfoBean> memberList) {
        ArrayAdapter<UserInfoBean> adapter = new ArrayAdapter<>(MainActivity.this,
                R.layout.item_spinner, R.id.tv_name, memberList);
        adapter.setDropDownViewResource(R.layout.item_spinner);
        spinnerUser.setAdapter(adapter);
        spinnerUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                memberId = memberList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    int rubbishCategoryId = 0;
    int point=0;
    @Override
    public void updateRubbishCategoryView(List<RubbishCategoryBean> categoryList) {
        ArrayAdapter<RubbishCategoryBean> adapter = new ArrayAdapter<>(MainActivity.this,
                R.layout.item_spinner, R.id.tv_name, categoryList);
        adapter.setDropDownViewResource(R.layout.item_spinner);
        spinnerCategory.setAdapter(adapter);
        tvPoint.setText(MessageFormat.format("获取积分： +{0}", categoryList.get(
                spinnerCategory.getSelectedItemPosition()).getPoint()));

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rubbishCategoryId = categoryList.get(position).getId();
                point = categoryList.get(position).getPoint();
                tvPoint.setText(MessageFormat.format("获取积分： +{0}", point));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    /**
     * 显示提示
     * @param msg
     */
    public void showInfo(String msg) {
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btn_save,R.id.photoSelectLayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                String weight = etWeight.getText().toString();
                mPresenter.rubbishRecycleAdd(memberId,rubbishCategoryId,Integer.valueOf(weight),point);
                break;
            case R.id.photoSelectLayout:
                Toast.makeText(MainActivity.this, "正在完善,敬请期待",Toast.LENGTH_SHORT).show();
                break;
        }
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
