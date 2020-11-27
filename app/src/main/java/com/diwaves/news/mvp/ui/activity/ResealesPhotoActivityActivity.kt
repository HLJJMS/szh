package com.diwaves.news.mvp.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.diwaves.news.R
import com.diwaves.news.adapter.AddPhotoAdapter
import com.diwaves.news.di.component.DaggerResealesPhotoActivityComponent
import com.diwaves.news.di.module.ResealesPhotoActivityModule
import com.diwaves.news.mvp.contract.ResealesPhotoActivityContract
import com.diwaves.news.mvp.presenter.ResealesPhotoActivityPresenter
import com.diwaves.news.tools.MyToast
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import com.zhihu.matisse.internal.entity.CaptureStrategy
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_reseales_photo.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/16/2020 20:38
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
class ResealesPhotoActivityActivity : BaseActivity<ResealesPhotoActivityPresenter>(),
    ResealesPhotoActivityContract.View {
    var adapter = AddPhotoAdapter()
    val photoCode = 1001
    var list: MutableList<String> = arrayListOf()
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerResealesPhotoActivityComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .resealesPhotoActivityModule(ResealesPhotoActivityModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_reseales_photo //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        titleBar.setBackClick {
            finish()
        }
        titleBar.setEndTextClick {
            var urlArry = ""
            for (index in 0 until list.size - 1) {
                urlArry = list.get(index) + ","
            }
            mPresenter?.postArticle(
                urlArry.substring(0, urlArry.length - 1),
                et_txt.text.toString(),
                "0",
                ""
            );
        }
        recyclerview.layoutManager = GridLayoutManager(this, 3)
        recyclerview.adapter = adapter
        list.add("")
        adapter.setList(list)
        adapter.setOnItemClickListener { adapter, view, position ->
            getPermissions()
        }
        getPermissions()
    }

    override fun postPhotoSuccess(url: String) {
        list.add(list.size - 1, url)
        adapter.setList(list)
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
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
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
                    "com.diwaves.news.photo"
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
                var file = File(Matisse.obtainPathResult(data)[i])
                val builder: MultipartBody.Builder = MultipartBody.Builder()
                builder.setType(MultipartBody.FORM)
                var requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
                builder.addFormDataPart("file", file.name, requestBody)
                mPresenter?.postImage(builder.build())
            }
        }
    }


}
