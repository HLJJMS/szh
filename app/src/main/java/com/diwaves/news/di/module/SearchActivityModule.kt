package com.diwaves.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.diwaves.news.mvp.contract.SearchActivityContract
import com.diwaves.news.mvp.model.SearchActivityModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/02/2020 10:18
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建SearchActivityModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class SearchActivityModule(private val view: SearchActivityContract.View) {
    @ActivityScope
    @Provides
    fun provideSearchActivityView(): SearchActivityContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideSearchActivityModel(model: SearchActivityModel): SearchActivityContract.Model {
        return model
    }
}
