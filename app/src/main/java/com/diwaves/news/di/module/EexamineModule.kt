package com.diwaves.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.diwaves.news.mvp.contract.EexamineContract
import com.diwaves.news.mvp.model.EexamineModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/12/2020 17:07
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建EexamineModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class EexamineModule(private val view: EexamineContract.View) {
    @ActivityScope
    @Provides
    fun provideEexamineView(): EexamineContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideEexamineModel(model: EexamineModel): EexamineContract.Model {
        return model
    }
}
