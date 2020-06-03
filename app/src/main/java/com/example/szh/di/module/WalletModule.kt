package com.example.szh.di.module

import com.jess.arms.di.scope.FragmentScope

import dagger.Module
import dagger.Provides

import com.example.szh.mvp.contract.WalletContract
import com.example.szh.mvp.model.WalletModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/03/2020 15:34
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建WalletModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class WalletModule(private val view: WalletContract.View) {
    @FragmentScope
    @Provides
    fun provideWalletView(): WalletContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideWalletModel(model: WalletModel): WalletContract.Model {
        return model
    }
}
