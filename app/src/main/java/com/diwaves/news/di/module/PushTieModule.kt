package com.diwaves.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.diwaves.news.mvp.contract.PushTieContract
import com.diwaves.news.mvp.model.PushTieModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/10/2020 17:24
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建PushTieModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class PushTieModule(private val view: PushTieContract.View) {
    @ActivityScope
    @Provides
    fun providePushTieView(): PushTieContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun providePushTieModel(model: PushTieModel): PushTieContract.Model {
        return model
    }
}
