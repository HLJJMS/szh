package com.example.szh.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.example.szh.di.component.DaggerListComponent
import com.example.szh.di.module.ListModule
import com.example.szh.mvp.contract.ListContract
import com.example.szh.mvp.presenter.ListPresenter

import com.example.szh.R
import com.example.szh.adapter.BangdanAdapter
import com.example.szh.adapter.CaiJingAdapter
import com.example.szh.bean.BangdanBean
import com.example.szh.mvp.ui.activity.RmbMaketMainActivity
import com.example.szh.mvp.ui.activity.TypeListActivityActivity
import com.jakewharton.rxbinding3.view.clicks
import kotlinx.android.synthetic.main.fragment_list.*
import java.util.concurrent.TimeUnit


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/19/2020 16:15
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
class ListFragment : BaseFragment<ListPresenter>(), ListContract.View {
    var bangdanAdapter: BangdanAdapter = BangdanAdapter()
    var bean: MutableList<BangdanBean.ResultBean>? = null
    var caiJingAdapter: CaiJingAdapter = CaiJingAdapter()

    companion object {
        fun newInstance(): ListFragment {
            val fragment = ListFragment()
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerListComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .listModule(ListModule(this))
            .build()
            .inject(this)
    }

    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        recycler_v.layoutManager = LinearLayoutManager(context)
        recycler_g.layoutManager = GridLayoutManager(context, 5)
        recycler_g.adapter = bangdanAdapter
        recycler_v.adapter = caiJingAdapter
        tv_zixun.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            tv_zixun.setTextColor(ContextCompat.getColor(mContext, R.color.color_020202))
            tv_caijing.setTextColor(ContextCompat.getColor(mContext, R.color.color_959595))
            bangdanAdapter.setList(bean?.get(0)?.dirsList)
            recycler_v.visibility = View.GONE
        }
        tv_caijing.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            tv_caijing.setTextColor(ContextCompat.getColor(mContext, R.color.color_020202))
            tv_zixun.setTextColor(ContextCompat.getColor(mContext, R.color.color_959595))
            bangdanAdapter.setList(bean?.get(1)?.dirsList)
            recycler_v.visibility = View.VISIBLE
            caiJingAdapter.setList(bean?.get(1)?.dirsList?.get(0)?.dirsList)
        }

        mPresenter?.getData()

        caiJingAdapter.setOnItemClickListener { adapter, view, position ->
            var intent = Intent(context,RmbMaketMainActivity::class.java)
            intent.putExtra("id",caiJingAdapter.data.get(position).id.toString())
            startActivity(intent)
        }
        bangdanAdapter.setOnItemClickListener { adapter, view, position ->
            startActivity(Intent(context, TypeListActivityActivity::class.java).putExtra("id",bangdanAdapter.data.get(position).id.toString()).putExtra("title",bangdanAdapter.data.get(position).title))
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

    override fun success(bean: MutableList<BangdanBean.ResultBean>) {
        this.bean = bean
        bangdanAdapter.setList(bean.get(0).dirsList)
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
}
