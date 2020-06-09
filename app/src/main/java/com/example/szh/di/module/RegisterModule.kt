package com.example.szh.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.example.szh.mvp.contract.RegisterContract
import com.example.szh.mvp.model.RegisterModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/09/2020 11:40
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建RegisterModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class RegisterModule(private val view: RegisterContract.View) {
    @ActivityScope
    @Provides
    fun provideRegisterView(): RegisterContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideRegisterModel(model: RegisterModel): RegisterContract.Model {
        return model
    }
}
