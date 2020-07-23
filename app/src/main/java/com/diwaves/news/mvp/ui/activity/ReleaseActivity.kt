package com.diwaves.news.mvp.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import com.diwaves.news.R
import com.diwaves.news.di.component.DaggerReleaseComponent
import com.diwaves.news.di.module.ReleaseModule
import com.diwaves.news.mvp.contract.ReleaseContract
import com.diwaves.news.mvp.presenter.ReleasePresenter
import com.diwaves.news.tools.CustomHtml
import com.diwaves.news.tools.MyToast
import com.diwaves.news.tools.SPToll
import com.jakewharton.rxbinding3.view.clicks
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import com.zhihu.matisse.internal.entity.CaptureStrategy
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_release.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.concurrent.TimeUnit


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/11/2020 23:04
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
class ReleaseActivity : BaseActivity<ReleasePresenter>(), ReleaseContract.View {
    var fans = true
    var friend = false
    var bold = false
    var italic = false
    var underline = false
    var photoCode = 1001;
    lateinit var file: File
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerReleaseComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .releaseModule(ReleaseModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_release //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        iv_b.clicks().throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                if (bold) {
                    bold = false
                    iv_b.setBackgroundResource(R.color.qmui_s_link_color)
                } else {
                    bold = true
                    iv_b.setBackgroundResource(R.color.qmui_config_color_10_white)
                }
                richEditText.setBold(bold)

            }
        iv_i.clicks().throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                if (italic) {
                    iv_i.setBackgroundResource(R.color.qmui_s_link_color)
                    italic = false
                } else {
                    italic = true
                    iv_i.setBackgroundResource(R.color.qmui_config_color_10_white)
                }
                richEditText.setItalic(italic)
            }
        iv_u.clicks().throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                if (underline) {
                    underline = false
                    iv_u.setBackgroundResource(R.color.qmui_s_link_color)
                } else {
                    underline = true
                    iv_u.setBackgroundResource(R.color.qmui_config_color_10_white)
                }
                richEditText.setUnderline(true)
            }
        iv_h.clicks().throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {

            }
        iv_img.clicks().throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                getPermissions()
            }
        iv_send_fans.clicks().throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                if (fans) {
                    fans = false
                    iv_send_fans.setImageResource(R.mipmap.ic_check_off)
                } else {
                    fans = true
                    iv_send_fans.setImageResource(R.mipmap.ic_check_on)
                }
            }
        iv_send_friend.clicks().throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                if (friend) {
                    friend = false
                    iv_send_friend.setImageResource(R.mipmap.ic_check_off)
                } else {
                    friend = true
                    iv_send_friend.setImageResource(R.mipmap.ic_check_on)
                }
            }
    }

    override fun postPhotoSuccess(url: String) {
        richEditText.setImg(url)
    }

    override fun postDataSuccess() {
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

    fun getPhoto() {
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
                file = File(Matisse.obtainPathResult(data)[i])

                val builder: MultipartBody.Builder = MultipartBody.Builder()
                builder.setType(MultipartBody.FORM)
                var requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
                builder.addFormDataPart("file", file.name, requestBody)
                mPresenter?.postImage(builder.build())
            }
        }
    }

    fun postdata(type: String) {
        val builder: MultipartBody.Builder = MultipartBody.Builder()
        builder.setType(MultipartBody.FORM)
        builder.addFormDataPart(
            "contenttext",
            CustomHtml.toHtml(
                richEditText.editableText,
                CustomHtml.TO_HTML_PARAGRAPH_LINES_CONSECUTIVE
            )
        )
        builder.addFormDataPart("dirid", "1")
        builder.addFormDataPart("dirname", "资讯")
        builder.addFormDataPart("state", type)

        builder.addFormDataPart("title", tv_title.text.toString())
        builder.addFormDataPart("state", type)


        builder.addFormDataPart("userid ", SPToll(this).getId())

        if (fans) {
            builder.addFormDataPart("tofans", "1")
        } else {
            builder.addFormDataPart("tofans", "0")
        }
        if (friend) {
            builder.addFormDataPart("tofriend", "1")
        } else {
            builder.addFormDataPart("tofriend", "0")
        }
        mPresenter?.postData(builder.build())
    }
}
