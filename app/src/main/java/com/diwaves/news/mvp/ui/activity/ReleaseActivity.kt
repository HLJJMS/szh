package com.diwaves.news.mvp.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.diwaves.news.R
import com.diwaves.news.adapter.SpnnerAdapter1
import com.diwaves.news.bean.BangdanBean
import com.diwaves.news.di.component.DaggerReleaseComponent
import com.diwaves.news.di.module.ReleaseModule
import com.diwaves.news.mvp.contract.ReleaseContract
import com.diwaves.news.mvp.presenter.ReleasePresenter
import com.diwaves.news.tools.ColorPickerView
import com.diwaves.news.tools.LoaddingView
import com.diwaves.news.tools.MyToast
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
    var adapter1: SpnnerAdapter1 = SpnnerAdapter1()
    var bean: MutableList<BangdanBean.ResultEntity>? = null
    var loaddingView: LoaddingView? = null
    var fans = true
    var friend = false
    var bold = false
    var photoCode = 1001;
    var dirname = "";
    var dirId = "";
    lateinit var file: File
    var isListOl = false
    var isAlignRight = false
    var isAlignLeft = false
    var isItalic = false
    var isTextLean = false
    var isListUL = false
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
        rv_1.layoutManager = GridLayoutManager(this, 5)
        rv_1.adapter = adapter1
        adapter1.addChildClickViewIds(R.id.tv_txt)
//        rv_2.layoutManager = GridLayoutManager(this, 5)
//        adapter2.addChildClickViewIds(R.id.tv_txt)
//        rv_2.adapter = adapter2
        richEditText.setEditorBackgroundColor(Color.WHITE);
        tv_spnner.setOnClickListener {
            rv_1.visibility = View.VISIBLE
//            rv_2.visibility = View.VISIBLE
        }

        iv_img.clicks().throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                getPermissions()
            }
        titlebar.setBackClick {
            finish()
        }
        titlebar.setEndTextClick {
            postdata("0")
        }
        titlebar.setStartTextClick {
            postdata("-1")
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
        adapter1.setOnItemClickListener { adapter, view, position ->
//            adapter2.setList(adapter1.data.get(position).dirsList)
            tv_spnner.setText(adapter1.data.get(position).title)
            dirId = adapter1.data.get(position).dirsList.get(adapter1.data.get(position).dirsList.size-1).id.toString()
            dirname = adapter1.data.get(position).title
            rv_1.visibility = View.GONE
        }
//        adapter2.setOnItemClickListener { adapter, view, position ->
//            tv_spnner.setText(adapter2.data.get(position).title)
//            dirId = adapter2.data.get(position).id.toString()
//            dirname = adapter2.data.get(position).title
//            rv_1.visibility = View.GONE
//            rv_2.visibility = View.GONE
//        }
        mPresenter?.getData()
        button_image.setOnClickListener {
            getPermissions()
        }
        tv_a.setOnClickListener {
            if (ll_setting.visibility == View.GONE) {
                ll_setting.visibility = View.VISIBLE
            } else {
                ll_setting.visibility = View.GONE
                ll_main_color.visibility = View.GONE
            }
        }
        button_text_color.setOnClickListener {
            if (ll_main_color.visibility == View.GONE) {
                ll_main_color.visibility = View.VISIBLE
            } else {
                ll_main_color.visibility = View.GONE
            }
        }
        button_bold.setOnClickListener {
            //字体加粗
            if (bold) {
                button_bold.setImageResource(R.mipmap.bold)
            } else {  //加粗
                button_bold.setImageResource(R.mipmap.bold_)
            }
            bold = !bold
            richEditText.setBold()
        }
        button_list_ol.setOnClickListener {
            if (isListOl) {
                button_list_ol.setImageResource(R.mipmap.list_ol)
            } else {
                button_list_ol.setImageResource(R.mipmap.list_ol_)
            }
            isListOl = !isListOl
            richEditText.setNumbers()
        }
        button_list_ul.setOnClickListener {
            if (isListUL) {
                button_list_ul.setImageResource(R.mipmap.list_ul)
            } else {
                button_list_ul.setImageResource(R.mipmap.list_ul_)
            }
            isListUL = !isListUL
            richEditText.setBullets()
        }
        button_underline.setOnClickListener {
            if (isTextLean) {
                button_underline.setImageResource(R.mipmap.underline)
            } else {
                button_underline.setImageResource(R.mipmap.underline_)
            }
            isTextLean = !isTextLean
            richEditText.setUnderline()
        }
        button_italic.setOnClickListener {
            if (isItalic) {
                button_italic.setImageResource(R.mipmap.lean)
            } else {
                button_italic.setImageResource(R.mipmap.lean_)
            }
            isItalic = !isItalic
            richEditText.setItalic()
        }
        button_align_left.setOnClickListener {
            if (isAlignLeft) {
                button_align_left.setImageResource(R.mipmap.align_left)
            } else {
                button_align_left.setImageResource(R.mipmap.align_left_)
            }
            isAlignLeft = !isAlignLeft
            richEditText.setAlignLeft()
        }
        button_align_right.setOnClickListener {
            if (isAlignRight) {
                button_align_right.setImageResource(R.mipmap.align_right)
            } else {
                button_align_right.setImageResource(R.mipmap.align_right_)
            }
            isAlignRight = !isAlignRight
            richEditText.setAlignRight()
        }

        iv_recall_no.setOnClickListener {
            richEditText.undo()
        }
        iv_recall.setOnClickListener {
            richEditText.redo()
            richEditText.setUrl()
        }

        cpv_main_color.setOnColorPickerChangeListener(object :
            ColorPickerView.OnColorPickerChangeListener {
            override fun onStartTrackingTouch(picker: ColorPickerView?) {

            }

            override fun onColorChanged(picker: ColorPickerView?, color: Int) {
                button_text_color.setBackgroundColor(color)
                richEditText.setTextColor(color)
            }

            override fun onStopTrackingTouch(picker: ColorPickerView?) {

            }

        })
    }


    override fun postPhotoSuccess(url: String) {
        //插入图片
        //这里的功能需要根据需求实现，通过insertImage传入一个URL或者本地图片路径都可以，这里用户可以自己调用本地相
        //或者拍照获取图片，传图本地图片路径，也可以将本地图片路径上传到服务器（自己的服务器或者免费的七牛服务器），
        //返回在服务端的URL地址，将地址传如即可（我这里传了一张写死的图片URL，如果你插入的图片不现实，请检查你是否添加
        // 网络请求权限<uses-permission android:name="android.permission.INTERNET" />）
        richEditText.insertImage(
            url,
            "dachshund"
        )
    }

    override fun postDataSuccess() {
        finish()
    }

    override fun success(bean: MutableList<BangdanBean.ResultEntity>) {

        bean.removeAt(0)
        bean.removeAt(1)
        bean.removeAt(bean.size - 1)
        bean.removeAt(bean.size - 1)
        bean.removeAt(bean.size - 1)
        bean.removeAt(bean.size - 1)
        bean.removeAt(bean.size - 1)
        bean.removeAt(bean.size - 1)
        bean.removeAt(bean.size - 1)
        bean.removeAt(bean.size - 1)
        bean.removeAt(0)
        adapter1.setList(bean)
    }


    override fun showLoading() {
        loaddingView?.show()
    }

    override fun hideLoading() {
        loaddingView?.dismiss()
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
                if (null == loaddingView) {
                    loaddingView = LoaddingView(this)
                }
                mPresenter?.postImage(builder.build())
            }
        }
    }

    fun postdata(type: String) {
        mPresenter?.postData(
            et_title.text.toString(), richEditText.html, dirId, dirname, type
        )
    }


}
