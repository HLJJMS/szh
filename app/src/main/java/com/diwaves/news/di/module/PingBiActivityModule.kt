package com.diwaves.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.diwaves.news.mvp.contract.PingBiActivityContract
import com.diwaves.news.mvp.model.PingBiActivityModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/29/2020 16:51
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建PingBiActivityModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class PingBiActivityModule(private val view: PingBiActivityContract.View) {
    @ActivityScope
    @Provides
    fun providePingBiActivityView(): PingBiActivityContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun providePingBiActivityModel(model: PingBiActivityModel): PingBiActivityContract.Model {
        return model
    }
}
