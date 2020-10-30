package com.diwaves.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.diwaves.news.mvp.contract.NewFriendContract
import com.diwaves.news.mvp.model.NewFriendModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 10/30/2020 17:10
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建NewFriendModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class NewFriendModule(private val view: NewFriendContract.View) {
    @ActivityScope
    @Provides
    fun provideNewFriendView(): NewFriendContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideNewFriendModel(model: NewFriendModel): NewFriendContract.Model {
        return model
    }
}
