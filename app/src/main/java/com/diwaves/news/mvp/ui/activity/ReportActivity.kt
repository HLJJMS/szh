package com.diwaves.news.mvp.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.diwaves.news.di.component.DaggerReportComponent
import com.diwaves.news.di.module.ReportModule
import com.diwaves.news.mvp.contract.ReportContract
import com.diwaves.news.mvp.presenter.ReportPresenter

import com.diwaves.news.R
import com.diwaves.news.tools.MyGlide
import com.diwaves.news.tools.MyToast
import com.diwaves.news.tools.SPToll
import com.tbruyelle.rxpermissions2.RxPermissions
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import com.zhihu.matisse.internal.entity.CaptureStrategy
import io.reactivex.functions.Consumer

import kotlinx.android.synthetic.main.activity_report.*
import kotlinx.android.synthetic.main.activity_report.iv_img
import kotlinx.android.synthetic.main.activity_report.titleBar
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/10/2020 16:26
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
class ReportActivity : BaseActivity<ReportPresenter>(), ReportContract.View {
    var photoCode = 1001;
    private var changePhoto: Boolean = false
    lateinit var file: File
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerReportComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .reportModule(ReportModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_report //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        titleBar.setBackClick {
            finish()
        }
        titleBar.setEndTextClick {
            var title = ""
            if (rb_yaoyan.isChecked) {
                 title = title + rb_yaoyan.text
            }
            if (rb_sexy.isChecked) {
                title = title + rb_sexy.text
            }
            if (rb_terror.isChecked) {
                title = title + rb_terror.text
            }
            if (rb_ad.isChecked) {
                title = title + rb_ad.text
            }
            if (rb_error.isChecked) {
                title = title + rb_error.text
            }
            if (rb_outdate.isChecked) {
                title = title + rb_outdate.text
            }
            if (rb_break.isChecked) {
                title = title + rb_break.text
            }
            if (rb_slow.isChecked) {
                title = title + rb_slow.text
            }
            if (rb_other.isChecked) {
                title = title + rb_other.text
            }
            if (rb_tort.isChecked) {
                title = title + rb_tort.text
            }
            val builder: MultipartBody.Builder = MultipartBody.Builder()
            builder.setType(MultipartBody.FORM)
            builder.addFormDataPart("userid", SPToll(this).getId())
            builder.addFormDataPart("objid", intent.getStringExtra("id"))
            builder.addFormDataPart("type", "1")
            builder.addFormDataPart("title", title )
            builder.addFormDataPart("text", et_txt.text.toString())
            if (changePhoto) {
                var requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
                builder.addFormDataPart("file", file.name, requestBody)
            }
            mPresenter?.postData(builder.build())


        }
        iv_img.setOnClickListener {
            getPermissions();
        }
    }

    override fun success() {
        finish()
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
    fun getPermissions() {
        val rxPermissions: RxPermissions = RxPermissions(this)
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
            .subscribe(Consumer<Boolean>() {
                if (it) {
                    getPhoto()
                } else {
                    MyToast().makeToast(this, "暂无权限")
                }
            });
    }

    private fun getPhoto() {
        Matisse.from(this)
            .choose(MimeType.ofAll()) //是否只显示选择的类型的缩略图，就不会把所有图片视频都放在一起，而是需要什么展示什么
            .showSingleMediaType(true) //这两行要连用 是否在选择图片中展示照相 和适配安卓7.0 FileProvider
            .capture(true)
            .captureStrategy(
                CaptureStrategy(
                    true,
                    "com.example.szh.photo"
                )
            ) //有序选择图片 123456...
            .countable(false) //最大选择数量为6
            .maxSelectable(1) //选择方向
            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) //界面中缩略图的质量
            .thumbnailScale(0.8f) //黑色主题
            .theme(R.style.Matisse_Dracula) //Glide加载方式
            .imageEngine(GlideEngine()) //请求码
            .forResult(photoCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == photoCode && resultCode == RESULT_OK) {
            for (i in Matisse.obtainPathResult(data).indices) {
                //解析文件
                changePhoto = true
                file = File(Matisse.obtainPathResult(data)[i])
                MyGlide.loadImage(this, file, iv_img)
            }
        }
    }}
