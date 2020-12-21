package com.diwaves.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.diwaves.news.mvp.contract.CaijingListContract
import com.diwaves.news.mvp.model.CaijingListModel


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
//构建CaijingListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class CaijingListModule(private val view: CaijingListContract.View) {
    @ActivityScope
    @Provides
    fun provideCaijingListView(): CaijingListContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideCaijingListModel(model: CaijingListModel): CaijingListContract.Model {
        return model
    }
}
