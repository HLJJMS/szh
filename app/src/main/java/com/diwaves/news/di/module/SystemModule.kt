package com.diwaves.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.diwaves.news.mvp.contract.SystemContract
import com.diwaves.news.mvp.model.SystemModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/16/2020 20:40
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建SystemModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class SystemModule(private val view: SystemContract.View) {
    @ActivityScope
    @Provides
    fun provideSystemView(): SystemContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideSystemModel(model: SystemModel): SystemContract.Model {
        return model
    }
}
