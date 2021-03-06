package com.diwaves.news.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager

import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.diwaves.news.di.component.DaggerMessageComponent
import com.diwaves.news.di.module.MessageModule
import com.diwaves.news.mvp.contract.MessageContract
import com.diwaves.news.mvp.presenter.MessagePresenter

import com.diwaves.news.R
import com.diwaves.news.adapter.MessageAdapter
import com.diwaves.news.bean.MessageBean
import com.diwaves.news.mvp.ui.activity.*
import com.jakewharton.rxbinding3.view.clicks
import kotlinx.android.synthetic.main.fragment_message.*
import kotlinx.android.synthetic.main.fragment_message.swipeLayout
import kotlinx.android.synthetic.main.fragment_recommend.*
import java.util.concurrent.TimeUnit


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/03/2020 17:34
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
class MessageFragment : BaseFragment<MessagePresenter>(), MessageContract.View {

    var adapter: MessageAdapter = MessageAdapter()

    companion object {
        fun newInstance(): MessageFragment {
            val fragment = MessageFragment()
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerMessageComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .messageModule(MessageModule(this))
            .build()
            .inject(this)
    }

    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_friend.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            startActivity(Intent(context, MyFriendActivity::class.java))
        }
        iv_system.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            startActivity(Intent(context, SystemActivity::class.java))
        }
        iv_shengao.clicks().throttleFirst(
            500, TimeUnit.MILLISECONDS
        ).subscribe {
            startActivity(Intent(context, EexamineActivity::class.java))
        }
        iv_add_friend.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            startActivity(Intent(context, NewFriendActivity::class.java))
        }
        iv_refresh.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            mPresenter?.getData()
        }
        iv_tousu.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            startActivity(Intent(context, TouSuActivityActivity::class.java))
        }
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = adapter
        swipeLayout.setOnRefreshListener {
            mPresenter?.getData()

        }
        mPresenter?.getData()

        adapter.setOnItemClickListener { adapter, view, position ->
            startActivity(
                Intent(activity, MessageDetailActivity::class.java).putExtra(
                    "id",
                    this.adapter.data.get(position).id
                ).putExtra("title" , this.adapter.data.get(position).name)
            )
        }
        adapter.addChildClickViewIds(R.id.tv_pingbi)
        adapter.setOnItemChildClickListener { adapter, view, position ->
            showDialog(this.adapter.data.get(position).id)
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

    override fun getDataSuccess(bean: MutableList<MessageBean.ResultEntity>) {
        swipeLayout.isRefreshing = false
        adapter?.setList(bean)
    }

    override fun pingbiSuccess() {
        swipeLayout.isRefreshing = true
        mPresenter?.getData()
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

    fun showDialog(id:String) {
        val normalDialog: AlertDialog.Builder = AlertDialog.Builder(mContext)
        normalDialog.setTitle("提示")
        normalDialog.setMessage("是否不接受该用户消息？");
        normalDialog.setPositiveButton(
            "确定"
        ) { dialog, which ->
            mPresenter?.pingbi(id)
        }
        normalDialog.setNegativeButton(
            "取消"
        ) { dialog, which ->

        }
        // 显示
        normalDialog.show()
    }


}
