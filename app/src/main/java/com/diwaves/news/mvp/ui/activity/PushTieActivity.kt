package com.diwaves.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.diwaves.news.di.component.DaggerPushTieComponent
import com.diwaves.news.di.module.PushTieModule
import com.diwaves.news.mvp.contract.PushTieContract
import com.diwaves.news.mvp.presenter.PushTiePresenter

import com.diwaves.news.R
import com.diwaves.news.adapter.PushTieAdapter
import com.diwaves.news.tools.MyGlide
import kotlinx.android.synthetic.main.activity_push_tie.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/10/2020 17:24
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
class PushTieActivity : BaseActivity<PushTiePresenter>(), PushTieContract.View {
    var auAdapter: PushTieAdapter = PushTieAdapter()
    var agAdapter: PushTieAdapter = PushTieAdapter()
    var listPerson: MutableList<String> = ArrayList<String>()
    var type="1"//1银2金
    var person = "10"
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerPushTieComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .pushTieModule(PushTieModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_push_tie //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        titleBar.setBackClick {
            finish()
        }
        titleBar.setEndTextClick {
            mPresenter?.postData(person,type,intent.getStringExtra("id"))
        }
        listPerson?.add("10")
        listPerson?.add("20")
        listPerson?.add("50")
        listPerson?.add("100")
        listPerson?.add("200")
        listPerson?.add("500")
        rv_ag.layoutManager = GridLayoutManager(this, 3)
        rv_au.layoutManager = GridLayoutManager(this, 3)
        rv_ag.adapter = agAdapter
        rv_au.adapter = auAdapter
        agAdapter.setList(listPerson)
        auAdapter.setList(listPerson)
        agAdapter.setOnItemClickListener { adapter, view, position ->
            agAdapter.setClickPosition(position)
            auAdapter.setClickPosition(100)
            person = listPerson?.get(position).toString()
            type = "1"
        }
        auAdapter.setOnItemClickListener { adapter, view, position ->
            auAdapter.setClickPosition(position)
            agAdapter.setClickPosition(100)
            person = listPerson?.get(position).toString()
            type="2"
        }
        agAdapter.setClickPosition(0)
        auAdapter.setClickPosition(100)
        tv_title.text = intent.getStringExtra("title")
        MyGlide.loadImage(this, intent.getStringExtra("img"), iv_img)

    }

    override fun success() {
        finish()
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
