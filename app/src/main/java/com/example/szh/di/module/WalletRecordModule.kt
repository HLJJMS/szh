package com.example.szh.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.example.szh.mvp.contract.WalletRecordContract
import com.example.szh.mvp.model.WalletRecordModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/17/2020 11:36
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建WalletRecordModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class WalletRecordModule(private val view: WalletRecordContract.View) {
    @ActivityScope
    @Provides
    fun provideWalletRecordView(): WalletRecordContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideWalletRecordModel(model: WalletRecordModel): WalletRecordContract.Model {
        return model
    }
}
