package com.example.szh.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.example.szh.mvp.contract.MyCommentContract
import com.example.szh.mvp.model.MyCommentModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/15/2020 19:31
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建MyCommentModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class MyCommentModule(private val view: MyCommentContract.View) {
    @ActivityScope
    @Provides
    fun provideMyCommentView(): MyCommentContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideMyCommentModel(model: MyCommentModel): MyCommentContract.Model {
        return model
    }
}
