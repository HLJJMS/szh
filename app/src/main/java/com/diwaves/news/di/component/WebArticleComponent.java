package com.diwaves.news.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.diwaves.news.di.module.WebArticleModule;
import com.diwaves.news.mvp.contract.WebArticleContract;

import com.jess.arms.di.scope.ActivityScope;
import com.diwaves.news.mvp.ui.activity.WebArticleActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 10/26/2020 14:41
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = WebArticleModule.class, dependencies = AppComponent.class)
public interface WebArticleComponent {
    void inject(WebArticleActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        WebArticleComponent.Builder view(WebArticleContract.View view);

        WebArticleComponent.Builder appComponent(AppComponent appComponent);

        WebArticleComponent build();
    }
}