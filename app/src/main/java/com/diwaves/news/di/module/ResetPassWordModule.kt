package com.diwaves.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.diwaves.news.mvp.contract.ResetPassWordContract
import com.diwaves.news.mvp.model.ResetPassWordModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/09/2020 11:56
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建ResetPassWordModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ResetPassWordModule(private val view: ResetPassWordContract.View) {
    @ActivityScope
    @Provides
    fun provideResetPassWordView(): ResetPassWordContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideResetPassWordModel(model: ResetPassWordModel): ResetPassWordContract.Model {
        return model
    }
}
