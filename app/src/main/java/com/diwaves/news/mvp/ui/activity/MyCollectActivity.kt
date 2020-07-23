package com.diwaves.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.diwaves.news.R
import com.diwaves.news.adapter.CollectAdapter
import com.diwaves.news.bean.CollectBean
import com.diwaves.news.di.component.DaggerMyCollectComponent
import com.diwaves.news.di.module.MyCollectModule
import com.diwaves.news.mvp.contract.MyCollectContract
import com.diwaves.news.mvp.presenter.MyCollectPresenter
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_my_collect.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/15/2020 17:49
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
class MyCollectActivity : BaseActivity<MyCollectPresenter>(), MyCollectContract.View {
    var adapter: CollectAdapter = CollectAdapter()
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMyCollectComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .myCollectModule(MyCollectModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_my_collect //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
        mPresenter?.getData()
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
    }

    override fun success(bean: MutableList<CollectBean.ResultBean.ListBean.RecordsBean>) {
        adapter.setList(bean)
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
