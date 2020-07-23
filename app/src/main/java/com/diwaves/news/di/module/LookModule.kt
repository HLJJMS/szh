package com.diwaves.news.di.module

import com.jess.arms.di.scope.FragmentScope

import dagger.Module
import dagger.Provides

import com.diwaves.news.mvp.contract.LookContract
import com.diwaves.news.mvp.model.LookModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/19/2020 16:16
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建LookModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class LookModule(private val view: LookContract.View) {
    @FragmentScope
    @Provides
    fun provideLookView(): LookContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideLookModel(model: LookModel): LookContract.Model {
        return model
    }
}
