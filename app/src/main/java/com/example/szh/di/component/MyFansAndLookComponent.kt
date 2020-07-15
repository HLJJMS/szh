package com.example.szh.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.example.szh.di.module.MyFansAndLookModule

import com.jess.arms.di.scope.ActivityScope
import com.example.szh.mvp.ui.activity.MyFansAndLookActivity


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/15/2020 20:04
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(
    modules = arrayOf(MyFansAndLookModule::class),
    dependencies = arrayOf(AppComponent::class)
)
interface MyFansAndLookComponent {
    fun inject(activity: MyFansAndLookActivity)
}
