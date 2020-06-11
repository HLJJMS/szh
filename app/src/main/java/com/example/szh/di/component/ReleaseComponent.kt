package com.example.szh.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.example.szh.di.module.ReleaseModule

import com.jess.arms.di.scope.ActivityScope
import com.example.szh.mvp.ui.activity.ReleaseActivity


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/11/2020 23:04
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = arrayOf(ReleaseModule::class), dependencies = arrayOf(AppComponent::class))
interface ReleaseComponent {
    fun inject(activity: ReleaseActivity)
}
