package com.example.szh.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.example.szh.di.component.DaggerRegisterComponent
import com.example.szh.di.module.RegisterModule
import com.example.szh.mvp.contract.RegisterContract
import com.example.szh.mvp.presenter.RegisterPresenter

import com.example.szh.R
import com.example.szh.tools.MyToast
import com.jakewharton.rxbinding3.view.clicks
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton
import kotlinx.android.synthetic.main.activity_register.*
import java.util.concurrent.TimeUnit


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/09/2020 11:40
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
class RegisterActivity : BaseActivity<RegisterPresenter>(), RegisterContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerRegisterComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .registerModule(RegisterModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_register //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        slider.setOnSlideCompleteListener {
            slider.setEnabled(false)
            slider.setText("验证完成")
        }
        tv_get_code.clicks().throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                if (et_phone.text.toString().length == 11) {
                    mPresenter?.getCode(et_phone.text.toString())
                    tv_get_code.isClickable = false
                    tv_get_code.setTextColor(ContextCompat.getColor(this, R.color.color_CECECE))
                } else {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_LONG).show()
                }

            }
        rb_ok.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            postData()
        }
        iv_close.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            finish()
        }

    }

    //获取验证码成功
    override fun getCodeSuccess() {
        tv_get_code.isClickable = false
        tv_get_code.setTextColor(ContextCompat.getColor(this, R.color.color_CECECE))
    }

    //获取验证码失败
    override fun getCodeFail() {
        tv_get_code.isClickable = true
        tv_get_code.setTextColor(ContextCompat.getColor(this, R.color.color_2BA4D9))
    }

    //发送数据
    fun postData() {
        if (!slider.textView.text.toString().equals("验证完成")) {
            MyToast().makeToast(this, "请完成滑动验证")
        } else if (et_phone.text.toString().length != 11) {
            MyToast().makeToast(this, "手机号有误")
        } else if (et_psd.text.toString().equals("") || et_ok_psd.text.toString()
                .equals("") || !et_psd.text.toString().equals(et_ok_psd.text.toString())
        ) {
            MyToast().makeToast(this, "密码有误")
        } else if (et_number.text.toString().equals("")) {
            MyToast().makeToast(this, "账号不能为空")
        } else {
            mPresenter?.postData(
                et_number.text.toString(),
                et_psd.text.toString(),
                et_phone.text.toString(),
                et_get_code.text.toString()
            )
        }


    }

    override fun showLoading() {
        var botton: QMUIRoundButton

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
