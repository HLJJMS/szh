package com.example.szh.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.example.szh.mvp.contract.TypeListActivityContract
import com.example.szh.mvp.model.TypeListActivityModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/22/2020 14:38
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建TypeListActivityModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class TypeListActivityModule(private val view: TypeListActivityContract.View) {
    @ActivityScope
    @Provides
    fun provideTypeListActivityView(): TypeListActivityContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideTypeListActivityModel(model: TypeListActivityModel): TypeListActivityContract.Model {
        return model
    }
}
