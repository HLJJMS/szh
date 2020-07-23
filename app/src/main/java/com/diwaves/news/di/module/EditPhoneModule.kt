package com.diwaves.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.diwaves.news.mvp.contract.EditPhoneContract
import com.diwaves.news.mvp.model.EditPhoneModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/10/2020 11:19
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建EditPhoneModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class EditPhoneModule(private val view: EditPhoneContract.View) {
    @ActivityScope
    @Provides
    fun provideEditPhoneView(): EditPhoneContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideEditPhoneModel(model: EditPhoneModel): EditPhoneContract.Model {
        return model
    }
}
