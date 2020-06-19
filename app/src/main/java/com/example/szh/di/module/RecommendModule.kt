package com.example.szh.di.module

import com.jess.arms.di.scope.FragmentScope

import dagger.Module
import dagger.Provides

import com.example.szh.mvp.contract.RecommendContract
import com.example.szh.mvp.model.RecommendModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/19/2020 14:56
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建RecommendModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class RecommendModule(private val view: RecommendContract.View) {
    @FragmentScope
    @Provides
    fun provideRecommendView(): RecommendContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideRecommendModel(model: RecommendModel): RecommendContract.Model {
        return model
    }
}
