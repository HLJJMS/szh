package com.diwaves.news.mvp.model

import android.app.Application
import com.diwaves.news.bean.WellatIndexBean
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.FragmentScope
import javax.inject.Inject

import com.diwaves.news.mvp.contract.WalletContract
import com.diwaves.news.network.bean.BaseBean
import com.diwaves.news.network.service.WalletService
import io.reactivex.Observable


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
@FragmentScope
class WalletModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    WalletContract.Model {
    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;
    override fun getData(id: String): Observable<BaseBean.BaseResponse<WellatIndexBean>> {
      return mRepositoryManager.obtainRetrofitService(WalletService::class.java).getWalletData(id)
    }

    override fun onDestroy() {
        super.onDestroy();
    }
}
