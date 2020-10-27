package com.diwaves.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.diwaves.news.mvp.contract.BuyVipContract
import com.diwaves.news.mvp.model.BuyVipModel


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
@Module
//构建BuyVipModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class BuyVipModule(private val view: BuyVipContract.View) {
    @ActivityScope
    @Provides
    fun provideBuyVipView(): BuyVipContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideBuyVipModel(model: BuyVipModel): BuyVipContract.Model {
        return model
    }
}
