package com.geek.rubbish.mvp.module.login.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.geek.rubbish.mvp.module.login.di.module.LoginModule;

import com.geek.rubbish.mvp.module.login.ui.activity.LoginActivity;

@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}