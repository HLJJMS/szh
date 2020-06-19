package com.example.szh.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.example.szh.di.module.RecommendModule

import com.jess.arms.di.scope.FragmentScope
import com.example.szh.mvp.ui.fragment.RecommendFragment


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/19/2020 14:56
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
@Component(modules = arrayOf(RecommendModule::class), dependencies = arrayOf(AppComponent::class))
interface RecommendComponent {
    fun inject(fragment: RecommendFragment)
}
