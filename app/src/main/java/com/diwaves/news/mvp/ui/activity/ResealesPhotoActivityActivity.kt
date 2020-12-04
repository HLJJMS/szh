package com.diwaves.news.mvp.ui.activity

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import androidx.core.os.EnvironmentCompat
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
import pl.aprilapps.easyphotopicker.*
import top.zibin.luban.CompressionPredicate
import top.zibin.luban.Luban
import top.zibin.luban.OnCompressListener
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


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
    val takePhotoCode = 1002
    var list: MutableList<String> = arrayListOf()
    var easyImage: EasyImage?=null
    //用于保存拍照图片的uri
    private var mCameraUri: Uri? = null

    // 用于保存图片的文件路径，Android 10以下使用图片路径访问图片
    private var mCameraImagePath: String? = null

    // 是否是Android 10以上手机
    private val isAndroidQ =
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

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
            getPermissions(false)
        }

        easyImage = EasyImage.Builder(this)
            .setChooserTitle("Pick media")
            .setCopyImagesToPublicGalleryFolder(false) //                .setChooserType(ChooserType.CAMERA_AND_DOCUMENTS)
            .setChooserType(ChooserType.CAMERA_AND_GALLERY)
            .setFolderName("EasyImage sample")
            .allowMultiple(true)
            .build()
        getPermissions(intent.getBooleanExtra("CAMERA", false))
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

    fun getPermissions(camear: Boolean) {
        val rxPermissions: RxPermissions = RxPermissions(this)
        rxPermissions.request(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
            .subscribe(Consumer<Boolean>() {
                if (it) {
                    if (camear) {
                        openCamera()
                    } else {
                        getPhoto()
                    }

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
                compress(file)
            }
        }else{
            easyImage?.handleActivityResult(
                requestCode,
                resultCode,
                data,
                this,
                object : DefaultCallback() {
                    override fun onMediaFilesPicked(
                        imageFiles: Array<MediaFile>,
                        source: MediaSource
                    ) {

                        compress(imageFiles.get(0).file)
                    }

                    override fun onImagePickerError(
                        error: Throwable,
                        source: MediaSource
                    ) {
                        //Some error handling
                        error.printStackTrace()
                    }

                    override fun onCanceled(source: MediaSource) {
                        //Not necessary to remove any files manually anymore
                    }
                })
        }





//        else if (requestCode == takePhotoCode && resultCode == RESULT_OK) {
//            if (isAndroidQ) {
//                // Android 10 使用图片uri加载
//                var file = File(mCameraUri.toString())
//                compress(mCameraUri.toString())
//            } else {
//                var file = File(mCameraImagePath)
//                compress(mCameraImagePath.toString())
//            }
//        }
    }

    /**
     * 调起相机拍照
     */
    private fun openCamera() {
//        val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        // 判断是否有相机
//        if (captureIntent.resolveActivity(packageManager) != null) {
//            var photoFile: File? = null
//            var photoUri: Uri? = null
//            if (isAndroidQ) {
//                // 适配android 10
//                photoUri = createImageUri()
//            } else {
//                try {
//                    photoFile = createImageFile()
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//                if (photoFile != null) {
//                    mCameraImagePath = photoFile.absolutePath
//                    photoUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                        //适配Android 7.0文件权限，通过FileProvider创建一个content类型的Uri
//                        FileProvider.getUriForFile(
//                            this,
//                            "com.diwaves.news.photo",
//                            photoFile
//                        )
//                    } else {
//                        Uri.fromFile(photoFile)
//                    }
//                }
//            }
//            mCameraUri = photoUri
//            if (photoUri != null) {
//                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
//                captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
//                startActivityForResult(captureIntent, takePhotoCode)
//            }
//        }
        easyImage?.openCameraForImage(this)
    }

    /**
     * 创建图片地址uri,用于保存拍照后的照片 Android 10以后使用这种方法
     */
    private fun createImageUri(): Uri? {
        val status: String = Environment.getExternalStorageState()
        // 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
        return if (status == Environment.MEDIA_MOUNTED) {
            contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                ContentValues()
            )
        } else {
            contentResolver.insert(
                MediaStore.Images.Media.INTERNAL_CONTENT_URI,
                ContentValues()
            )
        }
    }

    /**
     * 创建保存图片的文件
     */
    @Throws(IOException::class)
    private fun createImageFile(): File? {
        val imageName: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir =
            getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (!storageDir!!.exists()) {
            storageDir.mkdir()
        }
        val tempFile = File(storageDir, imageName)
        return if (Environment.MEDIA_MOUNTED != EnvironmentCompat.getStorageState(tempFile)) {
            null
        } else tempFile
    }

    fun compress(file1:File) {
        Luban.with(this)
            .load(file1)
            .ignoreBy(100)
            .setTargetDir(Environment.getExternalStorageDirectory().absolutePath)
            .setFocusAlpha(false)
            .setCompressListener(object : OnCompressListener, CompressionPredicate {
                override fun onSuccess(file: File?) {
                    val builder: MultipartBody.Builder = MultipartBody.Builder()
                    builder.setType(MultipartBody.FORM)
                    var requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
                    builder.addFormDataPart("file", file?.name, requestBody)
                    mPresenter?.postImage(builder.build())
                }

                override fun onError(e: Throwable?) {

                }

                override fun onStart() {

                }

                override fun apply(path: String?): Boolean {
                    return !(TextUtils.isEmpty(path) || path?.toLowerCase()!!.endsWith(".gif"));
                }

            })
    }




}


