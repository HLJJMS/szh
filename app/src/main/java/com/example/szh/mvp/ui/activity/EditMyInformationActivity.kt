package com.example.szh.mvp.ui.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import com.example.szh.R
import com.example.szh.bean.MyInfoBean
import com.example.szh.di.component.DaggerEditMyInformationComponent
import com.example.szh.di.module.EditMyInformationModule
import com.example.szh.mvp.contract.EditMyInformationContract
import com.example.szh.mvp.presenter.EditMyInformationPresenter
import com.example.szh.tools.MyGlide
import com.example.szh.tools.MyToast
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
import kotlinx.android.synthetic.main.activity_edit_my_information.*
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/10/2020 09:36
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
class EditMyInformationActivity : BaseActivity<EditMyInformationPresenter>(),
    EditMyInformationContract.View {
    var photoCode = 1001;
    lateinit var file: File
    lateinit var context: Context
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerEditMyInformationComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .editMyInformationModule(EditMyInformationModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_edit_my_information //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        context = this
        mPresenter?.getData()
        tv_phone.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            startActivity(Intent(this, EditPhoneActivity::class.java))
        }
        ll_head.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            getPermissions()
        }
    }

    override fun success(bean: MyInfoBean) {
        et_address.setText(bean.country + " " + bean.province + " " + bean.city)
        et_number.setText(bean.wxname)
        tv_phone.setText(bean.phone)
        tv_birthday.setText(bean.birthday)
        tv_sex.setText(bean.gender)
        tv_name.setText(bean.name)
        MyGlide.loadImageCircle(this, bean.avatarUrl, iv_head)
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
        var rxPermissions: RxPermissions = RxPermissions(this)
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
                MyGlide.loadImageCircle(context, file, iv_head)
            }
        }
    }


}
