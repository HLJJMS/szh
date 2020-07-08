package com.example.szh.mvp.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_COMPACT
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.example.szh.R
import com.example.szh.adapter.CommentAdapter
import com.example.szh.bean.ArticleDetailBean
import com.example.szh.bean.CommentBean
import com.example.szh.di.component.DaggerArticleDetailComponent
import com.example.szh.di.module.ArticleDetailModule
import com.example.szh.mvp.contract.ArticleDetailContract
import com.example.szh.mvp.presenter.ArticleDetailPresenter
import com.example.szh.tools.MyGlide
import com.example.szh.tools.MyToast
import com.example.szh.tools.SPToll
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
import kotlinx.android.synthetic.main.activity_article_detail.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
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
    var goodComment = "0"
    var collection: Int = 0
    var photoCode = 1001;
    lateinit var file: File
    private var changePhoto: Boolean = false
    private var cheak: String = "0" //0:全部可见 1:仅评论作者和帖子作者可见
    var type = "0"//0最热，1最新，2推+送
    var page = 1
    var adapter: CommentAdapter = CommentAdapter()
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
        et_comment.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                g_comment.visibility = View.VISIBLE
                g_comment_no.visibility = View.GONE
            } else {
                g_comment.visibility = View.GONE
                g_comment_no.visibility = View.VISIBLE
            }
        }

    }

    override fun getDataSuccess(bean: ArticleDetailBean.ResultBean) {
        titleBar.setCenterText(bean.articles.dirname)
        tv_look.setText(bean.articles.view.toString() + "阅读")
        tv_detail.text = Html.fromHtml(bean.articles.contenttext.toString(), FROM_HTML_MODE_COMPACT)
        if (null != bean.articles.pic) {
            MyGlide.loadImage(this, bean.articles.pic.toString(), iv_img)
        }
        tv_title.setText(bean.articles.title)
        like = bean.like
        collection = bean.collection
        isLikeOrCollection()
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
        iv_add_photo.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            getPermissions();
        }
        iv_check.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            if (cheak.equals("0")) {
                cheak = "1"
                iv_check.setImageResource(R.mipmap.ic_check_on)
            } else {
                cheak = "0"
                iv_check.setImageResource(R.mipmap.ic_check_off)
            }
        }

        tv_ok.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            if (changePhoto || !et_comment.text.toString().equals("")) {
                addComment()

            }
        }
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        mPresenter?.getComment(intent.getStringExtra("id"), page, type)
        initLoadMore()
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
        adapter.addChildClickViewIds(R.id.iv_good)
        adapter.setOnItemChildClickListener { adapter, view, position ->
            if(view.id==R.id.iv_good){
                if(this.adapter.data.get(position).isUp){
                    mPresenter?.goodComment(intent.getStringExtra("id"),this.adapter.data.get(position).id.toString(),"1")
                }else {
                    mPresenter?.goodComment(intent.getStringExtra("id"),this.adapter.data.get(position).id.toString(),"0")
                }
            }
        }
    }



    override fun getCommentListSuccess(bean: MutableList<CommentBean.ResultBean.RecordsBean>) {
        adapter.setList(bean)
        adapter.loadMoreModule.loadMoreComplete()
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

    fun isGoodComment(){

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == photoCode && resultCode == RESULT_OK) {
            for (i in Matisse.obtainPathResult(data).indices) {
                //解析文件
                changePhoto = true
                file = File(Matisse.obtainPathResult(data)[i])
                MyGlide.loadImageCircle(this, file, iv_add_photo)
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
        val builder: MultipartBody.Builder = MultipartBody.Builder()
        builder.setType(MultipartBody.FORM)
        builder.addFormDataPart("userid", SPToll(this).getId())
        builder.addFormDataPart("articleid", intent.getStringExtra("id"))
        builder.addFormDataPart("pushid", intent.getStringExtra("pushid"))
        builder.addFormDataPart("onlyauth", cheak)
        builder.addFormDataPart("content", et_comment.text.toString())
        if (changePhoto) {
            var requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
            builder.addFormDataPart("file", file.name, requestBody)
        }
        mPresenter?.addComment(builder.build())
    }




}
