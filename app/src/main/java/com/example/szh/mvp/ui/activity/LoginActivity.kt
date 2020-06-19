package com.example.szh.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.szh.R
import com.example.szh.di.component.DaggerLoginComponent
import com.example.szh.di.module.LoginModule
import com.example.szh.eventbus.MainEvent
import com.example.szh.mvp.contract.LoginContract
import com.example.szh.mvp.presenter.LoginPresenter
import com.example.szh.tools.LoaddingView
import com.example.szh.tools.MyToast
import com.jakewharton.rxbinding3.view.clicks
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_login.*
import org.greenrobot.eventbus.EventBus
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
    lateinit var loaddingView: LoaddingView
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
        loaddingView = LoaddingView(this)
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
        rb_ok.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            postData()
        }
        iv_close.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
           finish()
        }

    }

    override fun loginSuccess() {
        EventBus.getDefault().post(MainEvent(true));
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun loginFail() {
        slider.setEnabled(true)
        slider.setText("右滑验证")
    }

    override fun getCodeSuccess() {
        slider.setEnabled(false)
        slider.setText("验证完成")
    }

    override fun getCodeFail() {

    }


    override fun showLoading() {
        loaddingView.show()
    }

    override fun hideLoading() {
        loaddingView.dismiss()
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


    fun postData() {
        if (isPsd) {
            if (et_phone.text.toString().equals("") || et_phone.text.toString().length != 11) {
                MyToast().makeToast(this, "手机号格式错误")
            } else if (et_psd.text.toString().equals("")) {
                MyToast().makeToast(this, "密码不可为空")
            } else if (!slider.textView.text.equals("验证完成")) {
                MyToast().makeToast(this, "验证未完成")
            } else {
                mPresenter?.postData("0", et_psd.text.toString(), et_phone.text.toString(), "")
            }
        } else {
            if (et_phone.text.toString().equals("") || et_phone.text.toString().length != 11) {
                MyToast().makeToast(this, "手机号格式错误")
            } else if (et_psd.text.toString().equals("")) {
                MyToast().makeToast(this, "验证码不可为空")
            } else if (!slider.textView.text.equals("验证完成")) {
                MyToast().makeToast(this, "验证未完成")
            } else {
                mPresenter?.postData("1", et_phone.text.toString(), "", et_psd.text.toString())
            }
        }


    }
}
