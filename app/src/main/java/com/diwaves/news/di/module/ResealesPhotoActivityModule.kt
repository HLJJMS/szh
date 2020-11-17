package com.diwaves.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.diwaves.news.mvp.contract.ResealesPhotoActivityContract
import com.diwaves.news.mvp.model.ResealesPhotoActivityModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/16/2020 20:38
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建ResealesPhotoActivityModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ResealesPhotoActivityModule(private val view: ResealesPhotoActivityContract.View) {
    @ActivityScope
    @Provides
    fun provideResealesPhotoActivityView(): ResealesPhotoActivityContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideResealesPhotoActivityModel(model: ResealesPhotoActivityModel): ResealesPhotoActivityContract.Model {
        return model
    }
}
