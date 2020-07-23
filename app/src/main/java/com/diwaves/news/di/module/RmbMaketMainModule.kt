package com.diwaves.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.diwaves.news.mvp.contract.RmbMaketMainContract
import com.diwaves.news.mvp.model.RmbMaketMainModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/09/2020 11:22
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建RmbMaketMainModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class RmbMaketMainModule(private val view: RmbMaketMainContract.View) {
    @ActivityScope
    @Provides
    fun provideRmbMaketMainView(): RmbMaketMainContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideRmbMaketMainModel(model: RmbMaketMainModel): RmbMaketMainContract.Model {
        return model
    }
}
