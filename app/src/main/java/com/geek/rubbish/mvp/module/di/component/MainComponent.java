package com.geek.rubbish.mvp.module.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.geek.rubbish.mvp.module.di.module.MainModule;

import com.geek.rubbish.mvp.module.mvp.ui.activity.MainActivity;

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}