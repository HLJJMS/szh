package com.diwaves.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.diwaves.news.R
import com.diwaves.news.di.component.DaggerResetPassWordComponent
import com.diwaves.news.di.module.ResetPassWordModule
import com.diwaves.news.mvp.contract.ResetPassWordContract
import com.diwaves.news.mvp.presenter.ResetPassWordPresenter
import com.diwaves.news.tools.MyToast
import com.jakewharton.rxbinding3.view.clicks
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_reset_pass_word.et_get_code
import kotlinx.android.synthetic.main.activity_reset_pass_word.et_ok_psd
import kotlinx.android.synthetic.main.activity_reset_pass_word.et_phone
import kotlinx.android.synthetic.main.activity_reset_pass_word.et_psd
import kotlinx.android.synthetic.main.activity_reset_pass_word.rb_ok
import kotlinx.android.synthetic.main.activity_reset_pass_word.slider
import kotlinx.android.synthetic.main.activity_reset_pass_word.tv_get_code
import java.util.concurrent.TimeUnit


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/09/2020 11:56
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
class ResetPassWordActivity : BaseActivity<ResetPassWordPresenter>(), ResetPassWordContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerResetPassWordComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .resetPassWordModule(ResetPassWordModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_reset_pass_word //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        slider.setOnSlideCompleteListener {
            slider.setEnabled(false)
            slider.setText("验证完成")
        }
        tv_get_code.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
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

    }

    override fun resetSuccess() {
        finish()
    }

    override fun resetFail() {

    }

    override fun getCodeSuccess() {
        slider.setEnabled(false)
        slider.setText("验证完成")
    }

    override fun getCodeFail() {
        slider.setEnabled(true)
        slider.setText("右滑验证")
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
        } else {
            mPresenter?.postData(
                et_psd.text.toString(),
                et_phone.text.toString(),
                et_get_code.text.toString()
            )
        }


    }
}
