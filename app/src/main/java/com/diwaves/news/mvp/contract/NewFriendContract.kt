package com.diwaves.news.mvp.contract

import com.diwaves.news.bean.NewFriendBean
import com.diwaves.news.bean.YuCeBean
import com.diwaves.news.network.bean.BaseBean
import com.jess.arms.mvp.IView
import com.jess.arms.mvp.IModel
import io.reactivex.Observable


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 10/30/2020 17:10
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
interface NewFriendContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView{
      fun getDataSuccess(list:MutableList<NewFriendBean.ResultEntity>)
    }


    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel{
        fun getData(
            id: String
        ): Observable<NewFriendBean>
        fun okAndNo(
            id: String,type:String
        ): Observable<BaseBean.BaseResponse<Any>>
    }

}
