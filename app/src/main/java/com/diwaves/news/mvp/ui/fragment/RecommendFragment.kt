package com.diwaves.news.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.diwaves.news.di.component.DaggerRecommendComponent
import com.diwaves.news.di.module.RecommendModule
import com.diwaves.news.mvp.contract.RecommendContract
import com.diwaves.news.mvp.presenter.RecommendPresenter

import com.diwaves.news.R
import com.diwaves.news.adapter.RecommendAdapter
import com.diwaves.news.bean.RecommendBean
import com.diwaves.news.mvp.ui.activity.ArticleDetailActivity
import com.diwaves.news.mvp.ui.activity.PushTieActivity
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
        recommendAdapter.setOnItemClickListener { adapter, view, position ->
            var intent: Intent = Intent(context,ArticleDetailActivity::class.java)
            intent.putExtra("id", recommendAdapter.data.get(position).id.toString())
            if (null == recommendAdapter.data.get(position).pushid || "null".equals(recommendAdapter.data.get(position).pushid)) {
                intent.putExtra("pushid", "")
            } else {
                intent.putExtra("pushid", recommendAdapter.data.get(position).pushid.toString())
            }
            startActivity(intent)
        }
        recommendAdapter.addChildClickViewIds(R.id.iv_go, R.id.tv_tui)
        recommendAdapter.setOnItemChildClickListener { adapters, view, position ->
            var intent = Intent(context, PushTieActivity::class.java)
            intent.putExtra("id", recommendAdapter.data.get(position).id.toString())
            intent.putExtra("img", recommendAdapter.data.get(position).avatarUrl)
            intent.putExtra("title", recommendAdapter.data.get(position).title)
            startActivity(intent)
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

    override fun success(list: MutableList<RecommendBean.ResultBean>) {
        recommendAdapter.setList(list)
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
