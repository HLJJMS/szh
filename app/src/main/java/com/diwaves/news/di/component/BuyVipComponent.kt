package com.diwaves.news.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.diwaves.news.di.module.BuyVipModule

import com.jess.arms.di.scope.ActivityScope
import com.diwaves.news.mvp.ui.activity.BuyVipActivity


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 10/26/2020 16:01
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = arrayOf(BuyVipModule::class), dependencies = arrayOf(AppComponent::class))
interface BuyVipComponent {
    fun inject(activity: BuyVipActivity)
}
