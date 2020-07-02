package com.example.szh.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.example.szh.di.component.DaggerSearchActivityComponent
import com.example.szh.di.module.SearchActivityModule
import com.example.szh.mvp.contract.SearchActivityContract
import com.example.szh.mvp.presenter.SearchActivityPresenter

import com.example.szh.R
import com.example.szh.adapter.SearchAdapter
import com.example.szh.bean.RecommendBean
import com.jakewharton.rxbinding3.view.clicks
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.recycler
import kotlinx.android.synthetic.main.activity_wallet_record.*
import java.util.concurrent.TimeUnit


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/02/2020 10:18
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
class SearchActivityActivity : BaseActivity<SearchActivityPresenter>(),
    SearchActivityContract.View {
    var adapter: SearchAdapter = SearchAdapter()
    var isUser: String = "0"
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerSearchActivityComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .searchActivityModule(SearchActivityModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_search //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        iv_back.clicks().throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                finish()
            }
        tv_user.clicks().throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                tv_user.setTextColor(ContextCompat.getColor(this, R.color.color_020202))
                tv_tiezi.setTextColor(ContextCompat.getColor(this, R.color.color_A4A4A4))
                isUser = "0"
            }

        tv_tiezi.clicks().throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                tv_tiezi.setTextColor(ContextCompat.getColor(this, R.color.color_020202))
                tv_user.setTextColor(ContextCompat.getColor(this, R.color.color_A4A4A4))
                isUser = "1"
            }
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        adapter.setOnItemClickListener { adapter, view, position ->
            var intent: Intent = Intent(this, ArticleDetailActivity::class.java)
            intent.putExtra("id", this.adapter.data.get(position).id.toString())
            if (null == this.adapter.data.get(position).pushid || "null".equals(this.adapter.data.get(position).pushid)) {
                intent.putExtra("pushid", "")
            } else {
                intent.putExtra("pushid", this.adapter.data.get(position).pushid.toString())
            }
            startActivity(intent)
        }
        et_search.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mPresenter?.getData(et_search.text.toString(), isUser)
                }
                return false
            }

        })

    }

    override fun success(list: MutableList<RecommendBean.ResultBean>) {
        adapter.setList(list)
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
