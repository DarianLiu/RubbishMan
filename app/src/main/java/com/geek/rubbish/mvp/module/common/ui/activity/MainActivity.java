package com.geek.rubbish.mvp.module.common.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.geek.rubbish.R;
import com.geek.rubbish.mvp.model.bean.RubbishCategoryBean;
import com.geek.rubbish.mvp.module.common.di.component.DaggerMainComponent;
import com.geek.rubbish.mvp.module.common.di.module.MainModule;
import com.geek.rubbish.mvp.module.common.contract.MainContract;
import com.geek.rubbish.mvp.module.common.presenter.MainPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.text.MessageFormat;
import java.util.List;

import butterknife.BindView;

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
        mPresenter.rubbishCategory();
    }

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
                int point = categoryList.get(position).getPoint();
                tvPoint.setText(MessageFormat.format("获取积分： +{0}", point));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
