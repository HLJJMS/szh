package com.example.szh.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.example.szh.mvp.contract.MyLookContract
import com.example.szh.mvp.model.MyLookModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/15/2020 14:27
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建MyLookModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class MyLookModule(private val view: MyLookContract.View) {
    @ActivityScope
    @Provides
    fun provideMyLookView(): MyLookContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideMyLookModel(model: MyLookModel): MyLookContract.Model {
        return model
    }
}
