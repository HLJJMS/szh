package com.example.szh.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.example.szh.mvp.contract.AddRmbContract
import com.example.szh.mvp.model.AddRmbModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/16/2020 14:50
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建AddRmbModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class AddRmbModule(private val view: AddRmbContract.View) {
    @ActivityScope
    @Provides
    fun provideAddRmbView(): AddRmbContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideAddRmbModel(model: AddRmbModel): AddRmbContract.Model {
        return model
    }
}
