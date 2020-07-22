package com.example.szh.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.example.szh.di.component.DaggerMyFriendComponent
import com.example.szh.di.module.MyFriendModule
import com.example.szh.mvp.contract.MyFriendContract
import com.example.szh.mvp.presenter.MyFriendPresenter

import com.example.szh.R
import com.example.szh.adapter.FriendListAdapter
import com.example.szh.bean.FriendListBean
import kotlinx.android.synthetic.main.activity_article_detail.*
import kotlinx.android.synthetic.main.activity_my_friend.*
import kotlinx.android.synthetic.main.activity_my_friend.recycler


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/10/2020 11:25
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
class MyFriendActivity : BaseActivity<MyFriendPresenter>(), MyFriendContract.View {
    var adapter: FriendListAdapter = FriendListAdapter()
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMyFriendComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .myFriendModule(MyFriendModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_my_friend //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        mPresenter?.getData()
        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mPresenter?.searcheData(s.toString())

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    override fun success(bean: FriendListBean) {
        adapter.setList(bean.result)
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
