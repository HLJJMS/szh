package com.diwaves.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.diwaves.news.mvp.contract.KLineContract
import com.diwaves.news.mvp.model.KLineModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 12/21/2020 20:19
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建KLineModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class KLineModule(private val view: KLineContract.View) {
    @ActivityScope
    @Provides
    fun provideKLineView(): KLineContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideKLineModel(model: KLineModel): KLineContract.Model {
        return model
    }
}
