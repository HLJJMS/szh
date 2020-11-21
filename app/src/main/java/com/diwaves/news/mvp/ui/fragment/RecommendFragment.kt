package com.diwaves.news.mvp.ui.fragment

import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diwaves.news.R
import com.diwaves.news.adapter.PopRadioAdapter
import com.diwaves.news.adapter.RecommendAdapter
import com.diwaves.news.bean.RecommendBean
import com.diwaves.news.bean.StockBean
import com.diwaves.news.di.component.DaggerRecommendComponent
import com.diwaves.news.di.module.RecommendModule
import com.diwaves.news.mvp.contract.RecommendContract
import com.diwaves.news.mvp.presenter.RecommendPresenter
import com.diwaves.news.mvp.ui.activity.ArticleDetailActivity
import com.diwaves.news.mvp.ui.activity.ReportActivity
import com.diwaves.news.mvp.ui.activity.TypeListActivityActivity
import com.diwaves.news.tools.MyToast
import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton
import kotlinx.android.synthetic.main.fragment_recommend.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/19/2020 14:56
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
 * @FragmentScope(請注意命名空間) class NullObjectPresenterByFragment
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */
//推荐
class RecommendFragment : BaseFragment<RecommendPresenter>(), RecommendContract.View {
    var recommendAdapter: RecommendAdapter = RecommendAdapter()
    var popupWindow: PopupWindow = PopupWindow()
    var tagList: MutableList<String> = arrayListOf()
    var view1: View? = null
    var rbOk: QMUIRoundButton? = null
    var rbNo: QMUIRoundButton? = null
    var tage = ""
    var id = ""
    var adapterPop: PopRadioAdapter = PopRadioAdapter()

    companion object {
        fun newInstance(): RecommendFragment {
            val fragment = RecommendFragment()
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerRecommendComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .recommendModule(RecommendModule(this))
            .build()
            .inject(this)
    }

    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = recommendAdapter
        mPresenter?.getData()
        mPresenter?.getStockData()
        recommendAdapter.setOnItemClickListener { adapter, view, position ->
            var intent: Intent = Intent(context, ArticleDetailActivity::class.java)
            intent.putExtra("id", recommendAdapter.data.get(position).id.toString())
            if (null == recommendAdapter.data.get(position).pushid || "null".equals(
                    recommendAdapter.data.get(
                        position
                    ).pushid
                )
            ) {
                intent.putExtra("pushid", "")
            } else {
                intent.putExtra("pushid", recommendAdapter.data.get(position).pushid.toString())
            }
            startActivity(intent)
        }
        recommendAdapter.addChildClickViewIds(R.id.iv_go, R.id.tv_tui, R.id.iv_close,R.id.rb_detail)
        recommendAdapter.setOnItemChildClickListener { adapters, view, position ->
            if (view.id == R.id.iv_close) {
                tagList = recommendAdapter.data.get(position).tags.split(",").toMutableList()
                id = recommendAdapter.data.get(position).dirid.toString()
                showListDialog()
            }else if(view.id==R.id.rb_detail){
                var intent = Intent(context, TypeListActivityActivity::class.java)
                intent.putExtra("id",recommendAdapter.data.get(position).dirid.toString())
                intent.putExtra("title","")
                startActivity(intent)
            }
        }
        swipeLayout.setOnRefreshListener {
            mPresenter?.getData()
            mPresenter?.getStockData()
        }
    }

    /**
     * 通过此方法可以使 Fragment 能够与外界做一些交互和通信, 比如说外部的 Activity 想让自己持有的某个 Fragment 对象执行一些方法,
     * 建议在有多个需要与外界交互的方法时, 统一传 {@link Message}, 通过 what 字段来区分不同的方法, 在 {@link #setData(Object)}
     * 方法中就可以 {@code switch} 做不同的操作, 这样就可以用统一的入口方法做多个不同的操作, 可以起到分发的作用
     * <p>
     * 调用此方法时请注意调用时 Fragment 的生命周期, 如果调用 {@link #setData(Object)} 方法时 {@link Fragment#onCreate(Bundle)} 还没执行
     * 但在 {@link #setData(Object)} 里却调用了 Presenter 的方法, 是会报空的, 因为 Dagger 注入是在 {@link Fragment#onCreate(Bundle)} 方法中执行的
     * 然后才创建的 Presenter, 如果要做一些初始化操作,可以不必让外部调用 {@link #setData(Object)}, 在 {@link #initData(Bundle)} 中初始化就可以了
     * <p>
     * Example usage:
     * <pre>
     *fun setData(data:Any) {
     *   if(data is Message){
     *       when (data.what) {
     *           0-> {
     *               //根据what 做你想做的事情
     *           }
     *           else -> {
     *           }
     *       }
     *   }
     *}
     *
     *
     *
     *
     *
     * // call setData(Object):
     * val data = Message();
     * data.what = 0;
     * data.arg1 = 1;
     * fragment.setData(data);
     * </pre>
     *
     * @param data 当不需要参数时 {@code data} 可以为 {@code null}
     */
    override fun setData(data: Any?) {

    }

    override fun success(list: MutableList<RecommendBean.ResultEntity>) {
        recommendAdapter.setList(list)
        setPopWindow()
    }

    override fun StockSuccess(bean: StockBean.ResultEntity) {
        swipeLayout.isRefreshing=false
        tv_one.setText(bean.map1.name)
        tv_two.setText(bean.map2.name)
        tv_three.setText(bean.map3.name)

        tv_one_center.setText(bean.map1.current)
        tv_two_center.setText(bean.map2.current)
        tv_three_center.setText(bean.map3.current)
        if (bean.map1.change_pct > 0) {
            tv_one_center.setTextColor(ContextCompat.getColor(mContext, R.color.qmui_config_color_red))
            tv_one_bottom.setText(bean.map1.change_pct.toString() + " " + bean.map1.percentage.toString())
            tv_one_bottom.setTextColor(ContextCompat.getColor(mContext, R.color.qmui_config_color_red))
        } else {
            tv_one_bottom.setTextColor(ContextCompat.getColor(mContext, R.color.color_5cd65c))
            tv_one_center.setTextColor(ContextCompat.getColor(mContext, R.color.color_5cd65c))
            tv_one_bottom.setText(bean.map1.change_pct.toString() + " " + bean.map1.percentage.toString())
        }

        if (bean.map2.change_pct > 0) {
            tv_two_center.setTextColor(ContextCompat.getColor(mContext, R.color.qmui_config_color_red))
            tv_two_bottom.setTextColor(ContextCompat.getColor(mContext, R.color.qmui_config_color_red))
            tv_two_bottom.setText(bean.map2.change_pct.toString() + " " + bean.map2.percentage.toString())
        } else {
            tv_two_center.setTextColor(ContextCompat.getColor(mContext, R.color.color_5cd65c))
            tv_two_bottom.setTextColor(ContextCompat.getColor(mContext, R.color.color_5cd65c))
            tv_two_bottom.setText(bean.map2.change_pct.toString() + " " + bean.map2.percentage.toString())
        }

        if (bean.map3.change_pct > 0) {
            tv_three_center.setTextColor(ContextCompat.getColor(mContext, R.color.qmui_config_color_red))
            tv_three_bottom.setTextColor(ContextCompat.getColor(mContext, R.color.qmui_config_color_red))
            tv_three_bottom.setText(bean.map3.change_pct.toString() + " " + bean.map3.percentage.toString())
        } else {
            tv_three_center.setTextColor(ContextCompat.getColor(mContext, R.color.color_5cd65c))
            tv_three_bottom.setTextColor(ContextCompat.getColor(mContext, R.color.color_5cd65c))
            tv_three_bottom.setText(bean.map3.change_pct.toString() + " " + bean.map3.percentage.toString())
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

    }

    fun setPopWindow() {

        var recyclerViewPop: RecyclerView? = null
        view1 = LayoutInflater.from(mContext).inflate(R.layout.pop_pingbi_new, null);
        popupWindow.contentView = view1
        popupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true)
        popupWindow.setFocusable(true) //点击返回键取消
        popupWindow.setBackgroundDrawable(BitmapDrawable())
        recyclerViewPop = view1?.findViewById(R.id.recycler)
        recyclerViewPop?.layoutManager = LinearLayoutManager(mContext)
        recyclerViewPop?.adapter = adapterPop

        rbNo = view1?.findViewById(R.id.rb_off)
        rbOk = view1?.findViewById(R.id.rb_ok)
        rbOk?.setOnClickListener {
            mPresenter?.pingbi(id, tage)
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
            val lp = activity?.window?.attributes
            lp?.alpha = 1f
            activity?.window?.attributes = lp
        }
    }

    fun showPopWindow() {
        adapterPop.setNewInstance(tagList)
        val lp = activity?.window?.attributes
        lp?.alpha = 0.5f
        activity?.window?.attributes = lp
        popupWindow.showAtLocation(activity?.getWindow()?.getDecorView(), Gravity.CENTER, 0, 0)
    }

    private fun showListDialog() {
        val items = arrayOf("屏蔽", "投诉/举报", "删除文章", "取消")
        val listDialog: AlertDialog.Builder = AlertDialog.Builder(mContext)
        listDialog.setTitle("")
        listDialog.setItems(items, object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (which == 0) {
                    showPopWindow()
                } else if (which == 1) {
                    startActivity(
                        Intent(mContext, ReportActivity::class.java).putExtra("id", id)
                    )
                } else if (which == 2) {
                    MyToast().makeToast(mContext, "删除成功")
                }
            }

        })
        listDialog.show()
    }

    fun getData(){
        mPresenter?.getData()
        mPresenter?.getStockData()
    }
}
