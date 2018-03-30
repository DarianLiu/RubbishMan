package com.geek.rubbish.mvp.module.collection.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.geek.rubbish.mvp.module.collection.contract.CollectionContract;
import com.geek.rubbish.mvp.module.collection.model.CollectionModel;


@Module
public class CollectionModule {
    private CollectionContract.View view;

    /**
     * 构建CollectionModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public CollectionModule(CollectionContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    CollectionContract.View provideCollectionView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    CollectionContract.Model provideCollectionModel(CollectionModel model) {
        return model;
    }
}