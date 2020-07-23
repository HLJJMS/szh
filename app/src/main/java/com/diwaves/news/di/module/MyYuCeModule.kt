package com.diwaves.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.diwaves.news.mvp.contract.MyYuCeContract
import com.diwaves.news.mvp.model.MyYuCeModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/21/2020 15:13
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建MyYuCeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class MyYuCeModule(private val view: MyYuCeContract.View) {
    @ActivityScope
    @Provides
    fun provideMyYuCeView(): MyYuCeContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideMyYuCeModel(model: MyYuCeModel): MyYuCeContract.Model {
        return model
    }
}
