package com.example.szh.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.example.szh.mvp.contract.MainContract
import com.example.szh.mvp.model.MainModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/02/2020 21:37
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建MainModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class MainModule(private val view: MainContract.View) {
    @ActivityScope
    @Provides
    fun provideMainView(): MainContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideMainModel(model: MainModel): MainContract.Model {
        return model
    }
}
