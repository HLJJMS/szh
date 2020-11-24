package com.diwaves.news.mvp.ui.activity

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diwaves.news.R
import com.diwaves.news.adapter.CommentAdapter
import com.diwaves.news.adapter.PopRadioAdapter
import com.diwaves.news.bean.ArticleDetailBean
import com.diwaves.news.bean.CommentBean
import com.diwaves.news.di.component.DaggerArticleDetailComponent
import com.diwaves.news.di.module.ArticleDetailModule
import com.diwaves.news.mvp.contract.ArticleDetailContract
import com.diwaves.news.mvp.presenter.ArticleDetailPresenter
import com.diwaves.news.tools.MyGlide
import com.diwaves.news.tools.MyToast
import com.diwaves.news.tools.SoftKeyBoardListener
import com.jakewharton.rxbinding3.view.clicks
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton
import com.tbruyelle.rxpermissions2.RxPermissions
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import com.zhihu.matisse.internal.entity.CaptureStrategy
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_article_detail.*
import java.io.File
import java.util.concurrent.TimeUnit


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/27/2020 14:28
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
class ArticleDetailActivity : BaseActivity<ArticleDetailPresenter>(), ArticleDetailContract.View {
    var like: Int = 0
    var commentId = "";
    var collection: Int = 0
    var photoCode = 1001;
    lateinit var file: File
    var view: View? = null
    var rbOk: QMUIRoundButton? = null
    var rbNo: QMUIRoundButton? = null
    private var changePhoto: Boolean = false
    var recyclerView: RecyclerView? = null
    var arr: MutableList<String> = arrayListOf()
    var type = "0"//0最热，1最新，2推+送
    var page = 1
    var tage = ""
    var popupWindow: PopupWindow = PopupWindow()
    var adapter: CommentAdapter = CommentAdapter()
    var tagList: MutableList<String> = arrayListOf()
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerArticleDetailComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .articleDetailModule(ArticleDetailModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_article_detail //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        mPresenter?.getData(intent.getStringExtra("id"), intent.getStringExtra("pushid"))
        titleBar.setBackClick {
            finish()
        }
        onKeyBoardListener()

    }

    override fun getDataSuccess(bean: ArticleDetailBean.ResultBean) {
        titleBar.setCenterText(bean.articles.dirname + ">")
        tv_look.setText(bean.articles.view.toString() + "阅读")
        tv_title.setText(bean.articles.title)
        like = bean.like
        collection = bean.collection
        tv_fen.text = bean.articles.score + "次推荐"
        isLikeOrCollection()
        tv_name.setText(bean.articles.author)
        tv_time.setText(bean.articles.createdate)
        tv_introduction.setText(bean.articles.contenttext)
        if (null != bean.articles.tags) {
            arr = bean?.articles?.tags?.toString()?.split(",")!!.toMutableList()
        }
        MyGlide.loadImage(
            this,
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3976806040,3211395236&fm=11&gp=0.jpg",
            iv_head
        )
        for (index in 0..arr.size) {

        }
        tv_off.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            collection = Math.abs(collection - 1)
            mPresenter?.cllection(intent.getStringExtra("id"), collection.toString())
            isLikeOrCollection()
        }
        tv_like.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            like = Math.abs(like - 1)
            mPresenter?.like(intent.getStringExtra("id"), like.toString())
            isLikeOrCollection()
        }
        iv_star.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            collection = Math.abs(collection - 1)
            mPresenter?.cllection(intent.getStringExtra("id"), collection.toString())
            isLikeOrCollection()
        }
//        iv_add_photo.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
//            getPermissions();
//        }
//        iv_check.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
//            if (cheak.equals("0")) {
//                cheak = "1"
//                iv_check.setImageResource(R.mipmap.ic_check_on)
//            } else {
//                cheak = "0"
//                iv_check.setImageResource(R.mipmap.ic_check_off)
//            }
//        }
//        et_comment.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
//            var intent = Intent(this, PushTieActivity::class.java)
//            intent.putExtra("id", intent.getStringExtra("id"))
//            intent.putExtra("img", bean.articles.pic.toString())
//            intent.putExtra("title", bean.articles.title)
//            startActivity(intent)
//        }
        iv_send.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            var intent = Intent(this, PushTieActivity::class.java)
            intent.putExtra("id", intent.getStringExtra("id"))
            intent.putExtra("img", bean.articles.pic.toString())
            intent.putExtra("title", bean.articles.title)
            startActivity(intent)
        }



        iv_close.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            showListDialog()
        }

        tv_buy_vip.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            startActivity(Intent(this, BuyVipActivity::class.java))
        }
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter


        mPresenter?.getComment(intent.getStringExtra("id"), page, type)
        initLoadMore()
        setPopWindow()
        iv_send.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            var intent = Intent(this, PushTieActivity::class.java)
            intent.putExtra("id", intent.getStringExtra("id"))
            if (null != bean?.articles?.pic) {
                intent.putExtra("img", bean?.articles?.pic?.toString())
            }else{
                intent.putExtra("img","")
            }
            intent.putExtra("title", bean.articles.title)
            startActivity(intent)
        }


        rb_1.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            mPresenter?.sorce(intent.getStringExtra("id"), "1")
        }
        rb_2.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            mPresenter?.sorce(intent.getStringExtra("id"), "2")
        }
        rb_3.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            mPresenter?.sorce(intent.getStringExtra("id"), "3")
        }
        rb_4.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            mPresenter?.sorce(intent.getStringExtra("id"), "4")
        }
        rb_5.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            mPresenter?.sorce(intent.getStringExtra("id"), "5")
        }
        tv_detail.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            startActivity(
                Intent(this, WebArticleActivity::class.java).putExtra(
                    "url",
                    bean.articles.link
                ).putExtra("title", bean.articles.title.toString())
            )
        }

    }

    override fun commentSuccess() {

    }

    override fun getCommentListNull() {
        adapter.loadMoreModule.loadMoreEnd(true)
    }

    override fun getCommentListFail() {
        adapter.loadMoreModule.loadMoreFail()
    }


    /**
     * 初始化加载更多
     */
    private fun initLoadMore() {
        adapter.loadMoreModule.setOnLoadMoreListener {
            page++
            mPresenter?.getComment(intent.getStringExtra("id"), page, type)
        }
        adapter.loadMoreModule.isAutoLoadMore = true
        //当自动加载开启，同时数据不满一屏时，是否继续执行自动加载更多(默认为true)
        adapter.loadMoreModule.isEnableLoadMoreIfNotFullPage = false
        adapter.addChildClickViewIds(R.id.iv_good, R.id.tv_replay)
        adapter.setOnItemChildClickListener { adapter, view, position ->
            if (view.id == R.id.iv_good) {
                if (this.adapter.data.get(position).isUp) {
                    mPresenter?.goodComment(
                        intent.getStringExtra("id"),
                        this.adapter.data.get(position).id.toString(),
                        "1"
                    )
                } else {
                    mPresenter?.goodComment(
                        intent.getStringExtra("id"),
                        this.adapter.data.get(position).id.toString(),
                        "0"
                    )
                }
            } else if (view.id == R.id.tv_replay) {
                commentId = this.adapter.data.get(position).id.toString()
            }
        }
    }


    override fun getCommentListSuccess(bean: MutableList<CommentBean.ResultBean.RecordsBean>) {
        adapter.setList(bean)
        adapter.loadMoreModule.loadMoreComplete()
    }

    override fun pingbiSuccess() {
        popupWindow.dismiss()
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

    fun isLikeOrCollection() {
        if (collection == 0) {
            tv_off.text = "取消收藏"
        } else {
            tv_off.text = "收藏"
        }
        if (like == 0) {
            tv_like.text = "取消喜欢"
        } else {
            tv_like.text = "喜欢"
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == photoCode && resultCode == RESULT_OK) {
            for (i in Matisse.obtainPathResult(data).indices) {
                //解析文件
                changePhoto = true
                file = File(Matisse.obtainPathResult(data)[i])
            }
        }
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

    fun addComment() {
//        if (commentId.equals("")) {
//            val builder: MultipartBody.Builder = MultipartBody.Builder()
//            builder.setType(MultipartBody.FORM)
//            builder.addFormDataPart("userid", SPToll(this).getId())
//            builder.addFormDataPart("articleid", intent.getStringExtra("id"))
//            builder.addFormDataPart("pushid", intent.getStringExtra("pushid"))
//            builder.addFormDataPart("onlyauth", cheak)
//            builder.addFormDataPart("content", et_comment.text.toString())
//            if (changePhoto) {
//                var requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
//                builder.addFormDataPart("file", file.name, requestBody)
//            }
//            mPresenter?.addComment(builder.build())
//        } else {
//            mPresenter?.commentReplay(
//                intent.getStringExtra("id"),
//                commentId,
//                et_comment.text.toString()
//            )
//        }


    }


    //监听软件盘是否弹起
    private fun onKeyBoardListener() {
        SoftKeyBoardListener.setListener(
            this,
            object : SoftKeyBoardListener.OnSoftKeyBoardChangeListener {
                override fun keyBoardShow(height: Int) {

                }

                override fun keyBoardHide(height: Int) {
                    commentId = ""
                }

            })
    }

    fun setPopWindow() {
        var adapterPop: PopRadioAdapter = PopRadioAdapter()
        var recyclerViewPop: RecyclerView? = null

        view = LayoutInflater.from(this).inflate(R.layout.pop_pingbi_new, null);
        popupWindow.contentView = view
        popupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true)
        popupWindow.setFocusable(true) //点击返回键取消
        popupWindow.setBackgroundDrawable(BitmapDrawable())
        recyclerViewPop = view?.findViewById(R.id.recycler)
        recyclerViewPop?.layoutManager = LinearLayoutManager(this)
        recyclerViewPop?.adapter = adapterPop
        adapterPop.setList(arr)
        rbNo = view?.findViewById(R.id.rb_off)
        rbOk = view?.findViewById(R.id.rb_ok)
        rbOk?.setOnClickListener {
            mPresenter?.pingbi(intent.getStringExtra("id"), tage)
        }
        rbNo?.setOnClickListener {
            popupWindow.dismiss()
        }
        adapterPop.addChildClickViewIds(R.id.radio)
        adapterPop.setOnItemChildClickListener { adapter, view, position ->
            tage = adapterPop.data[position]
            adapterPop.setPositionAdapter(position)
        }
        popupWindow.setOnDismissListener {
            val lp = window.attributes
            lp.alpha = 1f
            window.attributes = lp
        }
    }

    fun showPopWindow() {
        val lp = window.attributes
        lp.alpha = 0.5f
        window.attributes = lp
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0)
    }

    private fun showListDialog() {
        val items = arrayOf("屏蔽", "投诉/举报", "删除文章", "取消")
        val listDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        listDialog.setTitle("")
        listDialog.setItems(items, object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (which == 0) {
                    showPopWindow()
                } else if (which == 1) {
                    startActivity(
                        Intent(this@ArticleDetailActivity, ReportActivity::class.java).putExtra(
                            "id",
                            intent.getStringExtra("id")
                        )
                    )
                } else if (which == 2) {
                    MyToast().makeToast(this@ArticleDetailActivity, "删除成功")
                }
            }

        })
        listDialog.show()
    }
}
