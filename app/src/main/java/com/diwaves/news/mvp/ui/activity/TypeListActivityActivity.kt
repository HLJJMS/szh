package com.diwaves.news.mvp.ui.activity

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.diwaves.news.di.component.DaggerTypeListActivityComponent
import com.diwaves.news.di.module.TypeListActivityModule
import com.diwaves.news.mvp.contract.TypeListActivityContract
import com.diwaves.news.mvp.presenter.TypeListActivityPresenter

import com.diwaves.news.R
import com.diwaves.news.adapter.CommentAdapter
import com.diwaves.news.adapter.PopRadioAdapter
import com.diwaves.news.adapter.TypeListAdapter
import com.diwaves.news.bean.TypeListBean
import com.diwaves.news.tools.MyToast
import com.jakewharton.rxbinding3.view.clicks
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton
import kotlinx.android.synthetic.main.activity_type_list.*
import java.util.concurrent.TimeUnit


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/22/2020 14:38
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
class TypeListActivityActivity : BaseActivity<TypeListActivityPresenter>(),
    TypeListActivityContract.View {
    var adapter: TypeListAdapter = TypeListAdapter()
    var type = "0"
    var page = 1
    var articleId = ""
    var view: View? = null
    var rbOk: QMUIRoundButton? = null
    var rbNo: QMUIRoundButton? = null
    var tage = ""
    var popupWindow: PopupWindow = PopupWindow()
    var tagList: MutableList<String> = arrayListOf()
    var adapterPop: PopRadioAdapter = PopRadioAdapter()
    var recyclerViewPop: RecyclerView? = null
    var context: Context? = null
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerTypeListActivityComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .typeListActivityModule(TypeListActivityModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_type_list //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        context = this
        titleBar.setBackClick {
            finish()
        }
        titleBar.setCenterText(intent.getStringExtra("title"))
        recycler.layoutManager = LinearLayoutManager(this)
        mPresenter?.getData(intent.getStringExtra("id"), type, page)
        recycler.adapter = adapter
        tv_hot.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            tv_hot.setTextColor(ContextCompat.getColor(this, R.color.color_137ED0))
            tv_new.setTextColor(ContextCompat.getColor(this, R.color.color_111111))
            type = "0"
            page = 1
            mPresenter?.getData(intent.getStringExtra("id"), type, page)
        }
        tv_new.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            tv_new.setTextColor(ContextCompat.getColor(this, R.color.color_137ED0))
            tv_hot.setTextColor(ContextCompat.getColor(this, R.color.color_111111))
            type = "1"
            page = 1
            mPresenter?.getData(intent.getStringExtra("id"), type, page)
        }
        adapter.loadMoreModule.setOnLoadMoreListener {
            page++
            mPresenter?.getData(intent.getStringExtra("id"), type, page)
        }
        adapter.loadMoreModule.isAutoLoadMore = true
        //当自动加载开启，同时数据不满一屏时，是否继续执行自动加载更多(默认为true)
        adapter.loadMoreModule.isEnableLoadMoreIfNotFullPage = false
        adapter.setOnItemClickListener { adapter, view, position ->
            var intent: Intent = Intent(this, ArticleDetailActivity::class.java)
            intent.putExtra("id", this.adapter.data.get(position).id.toString())
            if (null == this.adapter.data.get(position).pushid || "null".equals(
                    this.adapter.data.get(
                        position
                    ).pushid
                )
            ) {
                intent.putExtra("pushid", "")
            } else {
                intent.putExtra("pushid", this.adapter.data.get(position).pushid.toString())
            }
            startActivity(intent)
        }
        adapter.addChildClickViewIds(R.id.iv_close)
        adapter.setOnItemChildClickListener { adapters, view, position ->
            tagList = adapter.data.get(position).tags.split(",").toMutableList()
            articleId = adapter.data.get(position).id.toString()
        }
        setPopWindow()
        swipeLayout.setOnRefreshListener {
            page = 1
            mPresenter?.getData(intent.getStringExtra("id"), type, page)
        }
    }

    override fun sunccess(bean: MutableList<TypeListBean.ResultBean.RecordsBean>) {
        swipeLayout.isRefreshing = false
        if (page == 1) {
            adapter.setList(bean)
        } else {
            adapter.addData(bean)
        }
        if (bean.size == 0) {
            adapter.loadMoreModule.loadMoreEnd(true)
        } else {
            adapter.loadMoreModule.loadMoreComplete()
        }
        if (bean.size != 0) {
            titleBar.setCenterText(bean.get(0).dirname)
        }
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


    fun setPopWindow() {


        view = LayoutInflater.from(this).inflate(R.layout.pop_pingbi_new, null);
        popupWindow.contentView = view
        popupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true)
        popupWindow.setFocusable(true) //点击返回键取消
        popupWindow.setBackgroundDrawable(BitmapDrawable())
        recyclerViewPop = view?.findViewById(R.id.recycler)
        recyclerViewPop?.layoutManager = LinearLayoutManager(this)
        recyclerViewPop?.adapter = adapterPop
        adapterPop.setList(tagList)
        rbNo = view?.findViewById(R.id.rb_off)
        rbOk = view?.findViewById(R.id.rb_ok)
        rbOk?.setOnClickListener {
            mPresenter?.pingbi(articleId, tage)
        }
        rbNo?.setOnClickListener {
            popupWindow.dismiss()
        }
        adapterPop.addChildClickViewIds(R.id.radio)
        adapterPop.setOnItemChildClickListener { adapter, view, position ->
            tage = adapterPop.data[position]
            adapterPop.setPositionAdapter(position)
        }
        popupWindow.setOnDismissListener {
            val lp = window.attributes
            lp.alpha = 1f
            window.attributes = lp
        }
    }

    fun showPopWindow() {
        adapterPop.setList(tagList)
        val lp = window.attributes
        lp.alpha = 0.5f
        window.attributes = lp
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0)
    }

    private fun showListDialog() {
        val items = arrayOf("屏蔽", "投诉/举报", "删除文章", "取消")
        val listDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        listDialog.setTitle("")
        listDialog.setItems(items, object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (which == 0) {
                    showPopWindow()
                } else if (which == 1) {
                    startActivity(
                        Intent(context, ReportActivity::class.java).putExtra(
                            "id",
                            intent.getStringExtra("id")
                        )
                    )
                } else if (which == 2) {
                    MyToast().makeToast(this@TypeListActivityActivity, "删除成功")
                }
            }

        })
        listDialog.show()
    }
}
