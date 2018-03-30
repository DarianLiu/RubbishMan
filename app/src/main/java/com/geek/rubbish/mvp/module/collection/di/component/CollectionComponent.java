package com.geek.rubbish.mvp.module.collection.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.geek.rubbish.mvp.module.collection.di.module.CollectionModule;

import com.geek.rubbish.mvp.module.collection.ui.activity.CollectionActivity;

@ActivityScope
@Component(modules = CollectionModule.class, dependencies = AppComponent.class)
public interface CollectionComponent {
    void inject(CollectionActivity activity);
}