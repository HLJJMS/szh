package com.diwaves.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.diwaves.news.mvp.contract.FriendDetailContract
import com.diwaves.news.mvp.model.FriendDetailModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/10/2020 13:06
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建FriendDetailModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class FriendDetailModule(private val view: FriendDetailContract.View) {
    @ActivityScope
    @Provides
    fun provideFriendDetailView(): FriendDetailContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideFriendDetailModel(model: FriendDetailModel): FriendDetailContract.Model {
        return model
    }
}
