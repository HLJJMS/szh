package com.example.szh.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.example.szh.mvp.contract.ArticleDetailContract
import com.example.szh.mvp.model.ArticleDetailModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/27/2020 14:28
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建ArticleDetailModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ArticleDetailModule(private val view: ArticleDetailContract.View) {
    @ActivityScope
    @Provides
    fun provideArticleDetailView(): ArticleDetailContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideArticleDetailModel(model: ArticleDetailModel): ArticleDetailContract.Model {
        return model
    }
}
