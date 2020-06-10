package com.example.szh.mvp.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.example.szh.di.component.DaggerFriendDetailComponent
import com.example.szh.di.module.FriendDetailModule
import com.example.szh.mvp.contract.FriendDetailContract
import com.example.szh.mvp.presenter.FriendDetailPresenter

import com.example.szh.R
import com.example.szh.adapter.MyFramgentAdapter
import com.example.szh.bean.MyItemBean
import kotlinx.android.synthetic.main.fragment_my.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/10/2020 13:06
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
/**
 * 如果没presenter
 * 你可以这样写
 *
 * @ActivityScope(請注意命名空間) class NullObjectPresenterByActivity
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */
class FriendDetailActivity : BaseActivity<FriendDetailPresenter>(), FriendDetailContract.View {
    private var myFramgentAdapter: MyFramgentAdapter? = null
    private var list: ArrayList<MyItemBean>?= ArrayList()
    var context:Context? = null
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerFriendDetailComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .friendDetailModule(FriendDetailModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_friend_detail //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        context = this
        list?.add(MyItemBean("帖子", R.mipmap.ic_my_tieba, "0"))
        list?.add(MyItemBean("屏蔽", R.mipmap.ic_pingbi, "0"))
        list?.add(MyItemBean("处罚", R.mipmap.ic_chufa, "0"))
        myFramgentAdapter = MyFramgentAdapter(list)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = myFramgentAdapter
        myFramgentAdapter?.setList(list)
    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }
}
