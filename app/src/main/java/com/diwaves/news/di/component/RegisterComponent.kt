package com.diwaves.news.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.diwaves.news.di.module.RegisterModule

import com.jess.arms.di.scope.ActivityScope
import com.diwaves.news.mvp.ui.activity.RegisterActivity


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
@ActivityScope
@Component(modules = arrayOf(RegisterModule::class), dependencies = arrayOf(AppComponent::class))
interface RegisterComponent {
    fun inject(activity: RegisterActivity)
}
