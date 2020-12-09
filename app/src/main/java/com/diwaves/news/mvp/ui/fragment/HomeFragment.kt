package com.diwaves.news.mvp.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager

import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.diwaves.news.di.component.DaggerHomeComponent
import com.diwaves.news.di.module.HomeModule
import com.diwaves.news.mvp.contract.HomeContract
import com.diwaves.news.mvp.presenter.HomePresenter

import com.diwaves.news.R
import com.diwaves.news.adapter.HomePageAdapter
import com.diwaves.news.mvp.ui.activity.SearchActivityActivity
import com.jakewharton.rxbinding3.view.clicks
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.viewpager
import java.util.concurrent.TimeUnit


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/03/2020 17:31
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
class HomeFragment : BaseFragment<HomePresenter>(), HomeContract.View {
    private val mFragments = ArrayList<Fragment>()
    private var lookFragment = LookFragment()
    private val recommendFragment = RecommendFragment()
    private val listFragment = ListFragment()
    private var homePageAdapter: HomePageAdapter? = null

    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerHomeComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .homeModule(HomeModule(this))
            .build()
            .inject(this)
    }

    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    override fun initData(savedInstanceState: Bundle?) {
        mFragments.add(recommendFragment)
        mFragments.add(listFragment)
        mFragments.add(lookFragment)
        homePageAdapter = HomePageAdapter(childFragmentManager, mFragments)
        viewpager.adapter = homePageAdapter
        viewpager.setOffscreenPageLimit(2);
        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                setButton(position)
            }

        })
        tv_tuijian.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            viewpager.currentItem = 0
            v_tuijian.visibility = View.VISIBLE
            v_bangdan.visibility = View.GONE
            v_guanzhu.visibility = View.GONE
        }
        tv_bangdan.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            viewpager.currentItem = 1
            v_tuijian.visibility = View.GONE
            v_bangdan.visibility = View.VISIBLE
            v_guanzhu.visibility = View.GONE
        }
        tv_guanzhu.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            viewpager.currentItem = 2
            v_tuijian.visibility = View.GONE
            v_bangdan.visibility = View.GONE
            v_guanzhu.visibility = View.VISIBLE
        }
        iv_search.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            startActivity(Intent(context, SearchActivityActivity::class.java))
        }
    }

    fun setButton(number: Int) {
        if (number == 0) {
            tv_tuijian.setTextColor(ContextCompat.getColor(mContext, R.color.black))
            tv_bangdan.setTextColor(ContextCompat.getColor(mContext, R.color.color_959595))
            tv_guanzhu.setTextColor(ContextCompat.getColor(mContext, R.color.color_959595))
            v_tuijian.visibility = View.VISIBLE
            v_bangdan.visibility = View.GONE
            v_guanzhu.visibility = View.GONE
        } else if (number == 1) {
            tv_tuijian.setTextColor(ContextCompat.getColor(mContext, R.color.color_959595))
            tv_bangdan.setTextColor(ContextCompat.getColor(mContext, R.color.black))
            tv_guanzhu.setTextColor(ContextCompat.getColor(mContext, R.color.color_959595))
            v_tuijian.visibility = View.GONE
            v_bangdan.visibility = View.VISIBLE
            v_guanzhu.visibility = View.GONE
        } else {
            tv_tuijian.setTextColor(ContextCompat.getColor(mContext, R.color.color_959595))
            tv_bangdan.setTextColor(ContextCompat.getColor(mContext, R.color.color_959595))
            tv_guanzhu.setTextColor(ContextCompat.getColor(mContext, R.color.black))
            v_tuijian.visibility = View.GONE
            v_bangdan.visibility = View.GONE
            v_guanzhu.visibility = View.VISIBLE
        }
    }

    fun showPage2() {
        viewpager.currentItem = 2
        v_tuijian.visibility = View.GONE
        v_bangdan.visibility = View.VISIBLE
        v_guanzhu.visibility = View.GONE
    }


    fun shuaixnData() {
        recommendFragment.getData()
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
