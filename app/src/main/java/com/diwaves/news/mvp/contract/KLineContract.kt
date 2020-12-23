package com.diwaves.news.mvp.contract


import com.diwaves.news.bean.MyKLineBean
import com.diwaves.news.bean.KListBean
import com.diwaves.news.network.bean.BaseBean
import com.jess.arms.mvp.IView
import com.jess.arms.mvp.IModel
import io.reactivex.Observable


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 12/21/2020 20:19
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
interface KLineContract {
    interface View : IView{
        fun success(beanMy: MyKLineBean)
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel{
        fun getData(
            id: String,  type: String
        ): Observable<BaseBean.BaseResponse<KListBean>>

        fun getK(
              type: String
        ): Observable<BaseBean.BaseResponse<MyKLineBean>>
    }

}
