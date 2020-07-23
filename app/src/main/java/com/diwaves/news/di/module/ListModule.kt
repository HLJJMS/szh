package com.diwaves.news.di.module

import com.jess.arms.di.scope.FragmentScope

import dagger.Module
import dagger.Provides

import com.diwaves.news.mvp.contract.ListContract
import com.diwaves.news.mvp.model.ListModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/19/2020 16:15
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建ListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ListModule(private val view: ListContract.View) {
    @FragmentScope
    @Provides
    fun provideListView(): ListContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideListModel(model: ListModel): ListContract.Model {
        return model
    }
}
