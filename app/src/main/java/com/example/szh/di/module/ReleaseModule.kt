package com.example.szh.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.example.szh.mvp.contract.ReleaseContract
import com.example.szh.mvp.model.ReleaseModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/11/2020 23:04
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建ReleaseModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ReleaseModule(private val view: ReleaseContract.View) {
    @ActivityScope
    @Provides
    fun provideReleaseView(): ReleaseContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideReleaseModel(model: ReleaseModel): ReleaseContract.Model {
        return model
    }
}
