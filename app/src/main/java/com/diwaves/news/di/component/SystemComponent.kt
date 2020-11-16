package com.diwaves.news.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.diwaves.news.di.module.SystemModule

import com.jess.arms.di.scope.ActivityScope
import com.diwaves.news.mvp.ui.activity.SystemActivity


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/16/2020 20:40
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = arrayOf(SystemModule::class), dependencies = arrayOf(AppComponent::class))
interface SystemComponent {
    fun inject(activity: SystemActivity)
}
