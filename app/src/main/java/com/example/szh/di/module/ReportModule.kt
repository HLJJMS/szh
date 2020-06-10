package com.example.szh.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.example.szh.mvp.contract.ReportContract
import com.example.szh.mvp.model.ReportModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/10/2020 16:26
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建ReportModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ReportModule(private val view: ReportContract.View) {
    @ActivityScope
    @Provides
    fun provideReportView(): ReportContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideReportModel(model: ReportModel): ReportContract.Model {
        return model
    }
}
