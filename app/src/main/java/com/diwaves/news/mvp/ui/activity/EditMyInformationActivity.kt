package com.diwaves.news.mvp.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.diwaves.news.R
import com.diwaves.news.bean.JsonBean
import com.diwaves.news.bean.MyInfoBean
import com.diwaves.news.di.component.DaggerEditMyInformationComponent
import com.diwaves.news.di.module.EditMyInformationModule
import com.diwaves.news.mvp.contract.EditMyInformationContract
import com.diwaves.news.mvp.presenter.EditMyInformationPresenter
import com.diwaves.news.tools.MyGlide
import com.diwaves.news.tools.MyToast
import com.diwaves.news.tools.SPToll
import com.google.gson.Gson
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
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONArray
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*
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
    private val MSG_LOAD_DATA = 0x0001
    private val MSG_LOAD_SUCCESS = 0x0002
    private val MSG_LOAD_FAILED = 0x0003
    var isLoaded: Boolean = false
    private var options1Items: List<JsonBean> = ArrayList()
    private val options2Items =
        ArrayList<ArrayList<String>>()
    private val options3Items =
        ArrayList<ArrayList<ArrayList<String>>>()
    private var thread: Thread? = null
    private var province: String = ""
    private var city: String = ""
    private var changePhoto:Boolean = false
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
        initJsonData()
        tv_phone.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            startActivity(Intent(this, EditPhoneActivity::class.java))
        }
        ll_head.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            getPermissions()
        }
        ll_birthday.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            selectTime()
        }
        ll_address.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            setlectAddress()
        }
        ll_ok.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            saveData()
        }
        titleBar.setBackClick(View.OnClickListener {
            finish()
        })
    }

    override fun success(bean: MyInfoBean) {
        tv_address.setText(bean.user.country + " " + bean.user.province + " " + bean.user.city)
        province = bean.user.province
        city = bean.user.city
        et_number.setText(bean.user.wxname)
        tv_phone.setText(bean.user.phone)
        tv_birthday.setText(bean.user.birthday)
        tv_sex.setText(bean.user.gender)
        tv_name.setText(bean.user.name)
        MyGlide.loadImageCircle(this, bean.user.avatarUrl, iv_head)
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
                changePhoto = true
                file = File(Matisse.obtainPathResult(data)[i])
                MyGlide.loadImageCircle(context, file, iv_head)
            }
        }
    }

    fun selectTime() {
        val pvTime =
            TimePickerBuilder(this,
                OnTimeSelectListener { date, v ->
                    tv_birthday.setText(dateToString(date))
                })
                .setType(booleanArrayOf(true, true, true, false, false, false))// 默认全部显示
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .build()
        pvTime.show()
    }

    //date转换成yyyy-MM-dd
    fun dateToString(dateDate: Date?): String? {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return formatter.format(dateDate)
    }

    private fun setlectAddress() { //条件选择器初始化
        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */
        val pvOptions = OptionsPickerBuilder(this,
            OnOptionsSelectListener { options1, options2, options3, v -> //返回的分别是三个级别的选中位置
                val tx: String = (options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1)
                    .get(options2))
                province = options1Items.get(options1).getPickerViewText()
                city = options2Items.get(options1).get(options2)
                tv_address.setText(tx)

            })
            .setTitleText("城市选择")
            .setContentTextSize(20) //设置滚轮文字大小
            .setDividerColor(Color.LTGRAY) //设置分割线的颜色
            .setSelectOptions(0, 1) //默认选中项
            .setBgColor(Color.BLACK)
            .setTitleBgColor(Color.DKGRAY)
            .setTitleColor(Color.LTGRAY)
            .setCancelColor(Color.YELLOW)
            .setSubmitColor(Color.YELLOW)
            .setTextColorCenter(Color.LTGRAY)
            .isRestoreItem(true) //切换时是否还原，设置默认选中第一项。
            .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
            .setOutSideColor(0x00000000) //设置外部遮罩颜色
            .build<Any>()

        pvOptions.setPicker(options1Items, options2Items as List<MutableList<Any>>?) //二级选择器
        pvOptions.show()
    }

    private fun initJsonData() { //解析数据
        val JsonData: String =
            getJson(context, "province.json") //获取assets目录下的json文件数据
        val jsonBean: ArrayList<JsonBean> = parseData(JsonData) //用Gson 转成实体
        options1Items = jsonBean
        for (i in jsonBean.indices) { //遍历省份
            val cityList =
                ArrayList<String>() //该省的城市列表（第二级）
            val province_AreaList =
                ArrayList<ArrayList<String>>() //该省的所有地区列表（第三极）
            for (c in 0 until jsonBean[i].getCityList().size) { //遍历该省份的所有城市
                val cityName: String = jsonBean[i].getCityList().get(c).getName()
                cityList.add(cityName) //添加城市
                val city_AreaList =
                    ArrayList<String>() //该城市的所有地区列表
                city_AreaList.addAll(jsonBean[i].getCityList().get(c).getArea())
                province_AreaList.add(city_AreaList) //添加该省所有地区数据
            }
            /**
             * 添加城市数据
             */
            options2Items.add(cityList)
            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList)
        }
        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS)
    }

    fun parseData(result: String?): ArrayList<JsonBean> { //Gson 解析
        val detail: ArrayList<JsonBean> = ArrayList<JsonBean>()
        try {
            val data = JSONArray(result)
            val gson = Gson()
            for (i in 0 until data.length()) {
                val entity: JsonBean =
                    gson.fromJson(data.optJSONObject(i).toString(), JsonBean::class.java)
                detail.add(entity)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED)
        }
        return detail
    }

    @SuppressLint("HandlerLeak")
    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_LOAD_DATA -> if (thread == null) { //如果已创建就不再重新创建子线程了
                    thread = Thread(Runnable { // 子线程中解析省市区数据
                        initJsonData()
                    })
                    thread?.start()
                }
                MSG_LOAD_SUCCESS -> {
                    isLoaded = true
                }
                MSG_LOAD_FAILED ->
                    isLoaded = false
            }
        }
    }

    fun getJson(context: Context, fileName: String?): String {
        val stringBuilder = StringBuilder()
        try {
            val assetManager = context.assets
            val bf = BufferedReader(
                InputStreamReader(
                    assetManager.open(fileName!!)
                )
            )
            var line: String?
            while (bf.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }

    fun saveData() {
        val builder: MultipartBody.Builder = MultipartBody.Builder()
        builder.setType(MultipartBody.FORM)
        builder.addFormDataPart("userid",SPToll(context).getId())
        builder.addFormDataPart("name",tv_name.text.toString())
        builder.addFormDataPart("gender",tv_sex.text.toString())
        builder.addFormDataPart("birthday",tv_birthday.text.toString())
        builder.addFormDataPart("city",city)
        builder.addFormDataPart("province",province)
        if (changePhoto){
            var requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
            builder.addFormDataPart("avatarUrl", file.name, requestBody)
        }
        mPresenter?.postData(builder.build())
    }
}
