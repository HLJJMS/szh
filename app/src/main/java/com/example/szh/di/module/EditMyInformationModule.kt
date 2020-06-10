package com.example.szh.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.example.szh.mvp.contract.EditMyInformationContract
import com.example.szh.mvp.model.EditMyInformationModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/10/2020 09:36
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建EditMyInformationModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class EditMyInformationModule(private val view: EditMyInformationContract.View) {
    @ActivityScope
    @Provides
    fun provideEditMyInformationView(): EditMyInformationContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideEditMyInformationModel(model: EditMyInformationModel): EditMyInformationContract.Model {
        return model
    }
}
