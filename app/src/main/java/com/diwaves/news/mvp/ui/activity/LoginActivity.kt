package com.diwaves.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle

import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.diwaves.news.R
import com.diwaves.news.di.component.DaggerLoginComponent
import com.diwaves.news.di.module.LoginModule
import com.diwaves.news.eventbus.MainEvent
import com.diwaves.news.mvp.contract.LoginContract
import com.diwaves.news.mvp.presenter.LoginPresenter
import com.diwaves.news.network.Api.APP_ID
import com.diwaves.news.tools.LoaddingView
import com.diwaves.news.tools.MyToast
import com.jakewharton.rxbinding3.view.clicks
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
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
    var api: IWXAPI? = null
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
        tv_psw.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {

            isPsd = true
            tv_get_code.visibility = View.GONE
            et_psd.hint = "请输入密码"
            tv_psw.setTextColor(ContextCompat.getColor(this, R.color.black))
            tv_message.setTextColor(ContextCompat.getColor(this, R.color.color_959595))
        }
        tv_message.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            isPsd = false
            tv_get_code.visibility = View.VISIBLE
            et_psd.hint = "请输入验证码"
            tv_psw.setTextColor(ContextCompat.getColor(this, R.color.color_959595))
            tv_message.setTextColor(ContextCompat.getColor(this, R.color.black))
        }
        rb_ok.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            postData()
        }
        titleBar.setBackClick {
            finish()
        }

        iv_wx.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            var req: SendAuth.Req = SendAuth.Req();
            api = WXAPIFactory.createWXAPI(this, APP_ID, true);
            if (api?.isWXAppInstalled == true) {
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test";
                api?.sendReq(req);
            } else {
                MyToast().makeToast(this, "未安装微信")
            }
        }
        tv_register.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
          startActivity(Intent(this,RegisterActivity::class.java))
        }
        tv_reset.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            startActivity(Intent(this,ResetPassWordActivity::class.java))
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
