package com.diwaves.news.mvp.ui.activity


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
import com.diwaves.news.R
import com.diwaves.news.adapter.PopRadioAdapter
import com.diwaves.news.adapter.YuCeCommentAdapter
import com.diwaves.news.bean.YuCeCommentBean
import com.diwaves.news.bean.YuCeDetail
import com.diwaves.news.di.component.DaggerYuCeCommentComponent
import com.diwaves.news.di.module.YuCeCommentModule
import com.diwaves.news.mvp.contract.YuCeCommentContract
import com.diwaves.news.mvp.presenter.YuCeCommentPresenter
import com.jakewharton.rxbinding3.view.clicks
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton

import kotlinx.android.synthetic.main.activity_yu_ce_comment.*
import kotlinx.android.synthetic.main.activity_yu_ce_comment.swipeLayout
import kotlinx.android.synthetic.main.activity_yu_ce_comment.tv_k
import kotlinx.android.synthetic.main.activity_yu_ce_comment.tv_number
import kotlinx.android.synthetic.main.activity_yu_ce_comment.tv_time
import kotlinx.android.synthetic.main.activity_yu_ce_comment.tv_title
import java.util.concurrent.TimeUnit


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 12/23/2020 22:22
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
class YuCeCommentActivity : BaseActivity<YuCeCommentPresenter>(), YuCeCommentContract.View {
    var adapter: YuCeCommentAdapter = YuCeCommentAdapter()
    var view: View? = null
    var viewShenhe: View? = null
    var rbOk: QMUIRoundButton? = null
    var rbNo: QMUIRoundButton? = null
    var arr: MutableList<String> = arrayListOf()
    var type = "0"//0最热，1最新，2推+送
    var page = 1
    var tage = ""
    var commentId = ""
    var popupWindow: PopupWindow = PopupWindow()
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerYuCeCommentComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .yuCeCommentModule(YuCeCommentModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_yu_ce_comment //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        swipeLayout.setOnRefreshListener {
            mPresenter?.getData("1", intent.getStringExtra("zstype"))
        }
        tv_hot.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            tv_hot.setTextColor(ContextCompat.getColor(this, R.color.color_137ED0))
            tv_new.setTextColor(ContextCompat.getColor(this, R.color.color_020202))
            mPresenter?.getData("0", intent.getStringExtra("zstype"))
        }
        tv_new.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            tv_hot.setTextColor(ContextCompat.getColor(this, R.color.color_020202))
            tv_new.setTextColor(ContextCompat.getColor(this, R.color.color_137ED0))
            mPresenter?.getData("1", intent.getStringExtra("zstype"))
        }
        mPresenter?.getData("1", intent.getStringExtra("zstype"))
        mPresenter?.getDataUp(intent.getStringExtra("zstype"))
        adapter.addChildClickViewIds(R.id.iv_close1, R.id.iv_good)
        adapter.setOnItemChildClickListener { adapter, view, position ->
            if (view.id == R.id.iv_good) {
                mPresenter?.goodComment(this.adapter.data.get(position).id.toString(), "0")
            } else if (view.id == R.id.iv_close1) {
                commentId = this.adapter.data.get(position).id.toString()
                showCommentDialog(this.adapter.data.get(position).id.toString())
            }
        }

        setPopWindow()
    }

    override fun getCommentSuccess(list: MutableList<YuCeCommentBean>) {
        swipeLayout.isRefreshing = false
        adapter.setList(list)

    }

    override fun getUpSuccess(bean: YuCeDetail) {
        tv_date.setText(bean.d.predict.title)
        tv_time.setText(bean.d.predict.settlebegintime)
        tv_number.setText(intent.getStringExtra("number"))
        tv_type.setText(bean.d.predictStatus)
        tv_bet.setText("共注入" + bean.d.silver + "银币")
        tv_title.setText(intent.getStringExtra("title"))
        if (bean.d.silver.equals("0")) {
            tv_state.setText("您还未预测，前往预测")
        } else {
            tv_state.setText("您已经预测" + bean.d.silver + "银币" + "，继续预测")
        }
        tv_state.setOnClickListener {
            startActivity(
                Intent(this, RmbMaketMainActivity::class.java).putExtra(
                    "type",
                    intent.getStringExtra("zstype")
                )
            )
        }
        tv_k.setOnClickListener {
            startActivity(
                Intent(this, KLineActivity::class.java).putExtra(
                    "type",
                    intent.getStringExtra("zstype")
                )
                    .putExtra("title", intent.getStringExtra("title"))
                    .putExtra("number", intent.getStringExtra("number"))
            )
        }
    }

    override fun goodSuccess() {

    }

    override fun pingbiSuccess() {
        popupWindow.dismiss()
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

    private fun showCommentDialog(id: String) {
        val items = arrayOf("屏蔽", "投诉/举报", "取消")
        val listDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        listDialog.setTitle("")
        listDialog.setItems(items, object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (which == 0) {
                    showPopWindow()
                } else if (which == 1) {
                    startActivity(
                        Intent(this@YuCeCommentActivity, ReportActivity::class.java).putExtra(
                            "id",
                            id
                        )
                    )
                }
//                else if (which == 2) {
//                    MyToast().makeToast(this@ArticleDetailActivity, "删除成功")
//                }
            }

        })
        listDialog.show()
    }

    fun showPopWindow() {
        val lp = window.attributes
        lp.alpha = 0.5f
        window.attributes = lp
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0)
    }

    fun setPopWindow() {
        var adapterPop: PopRadioAdapter = PopRadioAdapter()
        var recyclerViewPop: RecyclerView? = null

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
        adapterPop.setList(arr)
        rbNo = view?.findViewById(R.id.rb_off)
        rbOk = view?.findViewById(R.id.rb_ok)
        rbOk?.setOnClickListener {
            mPresenter?.pingbi(commentId, tage)
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

}
