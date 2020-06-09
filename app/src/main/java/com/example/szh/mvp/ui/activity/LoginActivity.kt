package com.example.szh.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.szh.R
import com.example.szh.di.component.DaggerLoginComponent
import com.example.szh.di.module.LoginModule
import com.example.szh.mvp.contract.LoginContract
import com.example.szh.mvp.presenter.LoginPresenter
import com.jakewharton.rxbinding3.view.clicks
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.TimeUnit


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/05/2020 15:45
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
class LoginActivity : BaseActivity<LoginPresenter>(), LoginContract.View {
    var isPsd = true;
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .loginModule(LoginModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_login //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        slider.setOnSlideCompleteListener {
            slider.setEnabled(false)
            slider.setText("验证完成")
        }
        iv_phone.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            if (isPsd) {
                isPsd = false
                iv_phone.setImageResource(R.mipmap.ic_password)
                tv_phone.text = "密码登陆"
                tv_get_code.visibility = View.GONE
            } else {
                isPsd = true
                iv_phone.setImageResource(R.mipmap.ic_phone)
                tv_phone.text = "手机号登陆"
                tv_get_code.visibility = View.VISIBLE
            }
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
}
