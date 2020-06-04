package com.example.szh.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Message

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.example.szh.di.component.DaggerMyComponent
import com.example.szh.di.module.MyModule
import com.example.szh.mvp.contract.MyContract
import com.example.szh.mvp.presenter.MyPresenter

import com.example.szh.R
import com.example.szh.adapter.MyFramgentAdapter
import com.example.szh.bean.MyItemBean
import kotlinx.android.synthetic.main.fragment_my.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/03/2020 15:39
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
private var myFramgentAdapter: MyFramgentAdapter? = null
private var list: ArrayList<MyItemBean>

class MyFragment : BaseFragment<MyPresenter>(), MyContract.View {
    companion object {
        fun newInstance(): MyFragment {
            val fragment = MyFragment()
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerMyComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .myModule(MyModule(this))
            .build()
            .inject(this)
    }

    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        list?.add(MyItemBean("帖子", R.mipmap.ic_my_tieba, "0"))
        list?.add(MyItemBean("评论", R.mipmap.ic_my_message, "0"))
        list?.add(MyItemBean("预测", R.mipmap.ic_my_clock, "0"))
        list?.add(MyItemBean("屏蔽", R.mipmap.ic_pingbi, "0"))
        list?.add(MyItemBean("处罚", R.mipmap.ic_chufa, "0"))
        list?.add(MyItemBean("权限", R.mipmap.ic_quanxian, "0"))
        list?.add(MyItemBean("推荐", R.mipmap.ic_my_heart, "0"))
        list?.add(MyItemBean("收藏", R.mipmap.ic_start, "0"))
        list?.add(MyItemBean("草稿箱", R.mipmap.ic_test, "0"))
        myFramgentAdapter = MyFramgentAdapter(list)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = myFramgentAdapter
        myFramgentAdapter?.setList(list)
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
