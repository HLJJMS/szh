package com.diwaves.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.diwaves.news.mvp.contract.MyCollectContract
import com.diwaves.news.mvp.model.MyCollectModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/15/2020 17:49
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建MyCollectModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class MyCollectModule(private val view: MyCollectContract.View) {
    @ActivityScope
    @Provides
    fun provideMyCollectView(): MyCollectContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideMyCollectModel(model: MyCollectModel): MyCollectContract.Model {
        return model
    }
}
