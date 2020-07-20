package com.example.szh.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.example.szh.di.module.MyArticleModule

import com.jess.arms.di.scope.ActivityScope
import com.example.szh.mvp.ui.activity.MyArticleActivity


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/20/2020 18:02
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = arrayOf(MyArticleModule::class), dependencies = arrayOf(AppComponent::class))
interface MyArticleComponent {
    fun inject(activity: MyArticleActivity)
}
