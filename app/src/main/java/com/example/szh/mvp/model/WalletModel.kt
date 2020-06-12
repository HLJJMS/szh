package com.example.szh.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.FragmentScope
import javax.inject.Inject

import com.example.szh.mvp.contract.WalletContract
import com.example.szh.network.bean.BaseBean
import com.example.szh.network.service.LoginService
import com.example.szh.network.service.WalletService
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
    override fun getData(id: String): Observable<BaseBean.BaseResponse<String>> {
      return mRepositoryManager.obtainRetrofitService(WalletService::class.java).getWalletData(id)
    }

    override fun onDestroy() {
        super.onDestroy();
    }
}
