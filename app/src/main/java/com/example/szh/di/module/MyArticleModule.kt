package com.example.szh.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.example.szh.mvp.contract.MyArticleContract
import com.example.szh.mvp.model.MyArticleModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/20/2020 18:02
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建MyArticleModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class MyArticleModule(private val view: MyArticleContract.View) {
    @ActivityScope
    @Provides
    fun provideMyArticleView(): MyArticleContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideMyArticleModel(model: MyArticleModel): MyArticleContract.Model {
        return model
    }
}
