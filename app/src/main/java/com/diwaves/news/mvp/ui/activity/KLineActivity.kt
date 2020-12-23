package com.diwaves.news.mvp.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.diwaves.news.R
import com.diwaves.news.bean.KLineBean
import com.diwaves.news.bean.MyKLineBean
import com.diwaves.news.di.component.DaggerKLineComponent
import com.diwaves.news.di.module.KLineModule
import com.diwaves.news.mvp.contract.KLineContract
import com.diwaves.news.mvp.presenter.KLinePresenter
import com.diwaves.news.tools.CoupleChartGestureListener
import com.diwaves.news.tools.DataParse
import com.diwaves.news.tools.MyUtils
import com.diwaves.news.tools.VolFormatter
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.Utils
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_k_line.*
import kotlinx.android.synthetic.main.activity_k_line.titleBar
import kotlinx.android.synthetic.main.activity_k_line.tv_number
import kotlinx.android.synthetic.main.activity_k_line.tv_title

import java.util.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 12/21/2020 20:19
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
class KLineActivity : BaseActivity<KLinePresenter>(), KLineContract.View {
    var mData: DataParse = DataParse()
    var xAxisBar: XAxis? = null
    var xAxisK: XAxis? = null
    var axisLeftBar: YAxis? = null
    var axisLeftK: YAxis? = null
    var axisRightBar: YAxis? = null
    var axisRightK: YAxis? = null
    var sum: Float = 0f
    private var kLineDatas: ArrayList<KLineBean>? = null
    var barDataSet: BarDataSet? = null
    private val handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            barChart.isAutoScaleMinMaxEnabled = true
            combinedchart.isAutoScaleMinMaxEnabled = true
            combinedchart.notifyDataSetChanged()
            barChart.notifyDataSetChanged()
            combinedchart.invalidate()
            barChart.invalidate()
        }
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerKLineComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .kLineModule(KLineModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_k_line //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        initChart()
        titleBar.setCenterText(intent.getStringExtra("title"))
        titleBar.setBackClick {
            finish()
        }
        mPresenter?.getK(intent.getStringExtra("type"))
    }


    override fun success(beanMy: MyKLineBean) {
        mData.parseKLine(beanMy)
        tv_title.setText(intent.getStringExtra("title"))
        tv_number.setText(intent.getStringExtra("number"))
        tv_time.setText(beanMy.info.date)
        tv_open.setText("开盘：" + beanMy.info.open)
        tv_height.setText("最高：" + beanMy.info.high)
        tv_rmb.setText("成交额：" + beanMy.info.money)
        tv_low.setText("最低：" + beanMy.info.low)
        tv_yesterday.setText("昨日：" + beanMy.info.close)
        setData(mData)

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

    private fun initChart() {
        barChart.setDrawBorders(true)
        barChart.setBorderWidth(1f)
        barChart.setBorderColor(resources.getColor(R.color.minute_grayLine))
        barChart.setDescription("")
        barChart.setDragEnabled(true)
        barChart.setScaleYEnabled(false)
        val barChartLegend: Legend = barChart.getLegend()
        barChartLegend.isEnabled = false

        //BarYAxisFormatter  barYAxisFormatter=new BarYAxisFormatter();
        //bar x y轴
        xAxisBar = barChart.getXAxis()
        xAxisBar?.setDrawLabels(true)
        xAxisBar?.setDrawGridLines(false)
        xAxisBar?.setDrawAxisLine(false)
        xAxisBar?.setTextColor(resources.getColor(R.color.minute_zhoutv))
        xAxisBar?.setPosition(XAxis.XAxisPosition.BOTTOM)
        xAxisBar?.setGridColor(resources.getColor(R.color.minute_grayLine))
        axisLeftBar = barChart.getAxisLeft()
        axisLeftBar?.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        axisLeftBar?.setAxisMinValue(0f)
        axisLeftBar?.setDrawGridLines(false)
        axisLeftBar?.setDrawAxisLine(false)
        axisLeftBar?.setTextColor(resources.getColor(R.color.minute_zhoutv))
        axisLeftBar?.setDrawLabels(true)
        axisLeftBar?.setSpaceTop(0f)
        axisLeftBar?.setShowOnlyMinMax(true)
        axisRightBar = barChart.getAxisRight()
        axisRightBar?.setDrawLabels(false)
        axisRightBar?.setDrawGridLines(false)
        axisRightBar?.setDrawAxisLine(false)
        /** */
        combinedchart.setDrawBorders(true)
        combinedchart.setBorderWidth(1f)
        combinedchart.setBorderColor(resources.getColor(R.color.minute_grayLine))
        combinedchart.setDescription("")
        combinedchart.setDragEnabled(true)
        combinedchart.setScaleYEnabled(false)
        val combinedchartLegend: Legend = combinedchart.getLegend()
        combinedchartLegend.isEnabled = false
        //bar x y轴
        xAxisK = combinedchart.getXAxis()
        xAxisK?.setDrawLabels(true)
        xAxisK?.setDrawGridLines(true)
        xAxisK?.setDrawAxisLine(false)
        xAxisK?.setTextColor(resources.getColor(R.color.minute_zhoutv))
        xAxisK?.setPosition(XAxis.XAxisPosition.BOTTOM)
        xAxisK?.setGridColor(resources.getColor(R.color.minute_grayLine))
        axisLeftK = combinedchart.getAxisLeft()
        axisLeftK?.setDrawGridLines(true)
        axisLeftK?.setDrawAxisLine(false)
        axisLeftK?.setDrawLabels(true)
        axisLeftK?.setTextColor(resources.getColor(R.color.minute_zhoutv))
        axisLeftK?.setGridColor(resources.getColor(R.color.minute_grayLine))
        axisLeftK?.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        axisRightK = combinedchart.getAxisRight()
        axisRightK?.setDrawLabels(false)
        axisRightK?.setDrawGridLines(true)
        axisRightK?.setDrawAxisLine(false)
        axisRightK?.setGridColor(resources.getColor(R.color.minute_grayLine))
        combinedchart.setDragDecelerationEnabled(true)
        barChart.setDragDecelerationEnabled(true)
        combinedchart.setDragDecelerationFrictionCoef(0.2f)
        barChart.setDragDecelerationFrictionCoef(0.2f)


        // 将K线控的滑动事件传递给交易量控件
        combinedchart.setOnChartGestureListener(
            CoupleChartGestureListener(
                combinedchart,
                arrayOf<Chart<*>>(barChart)
            )
        )

        // 将交易量控件的滑动事件传递给K线控件
        barChart.setOnChartGestureListener(
            CoupleChartGestureListener(
                barChart,
                arrayOf<Chart<*>>(combinedchart)
            )
        )
        barChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(
                e: Entry,
                dataSetIndex: Int,
                h: Highlight
            ) {
                Log.e("%%%%", h.xIndex.toString() + "")
                combinedchart.highlightValues(
                    arrayOf(
                        h
                    )
                )
            }

            override fun onNothingSelected() {
                combinedchart.highlightValue(null)
            }
        })
        combinedchart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(
                e: Entry,
                dataSetIndex: Int,
                h: Highlight
            ) {
                barChart.highlightValues(arrayOf(h))
            }

            override fun onNothingSelected() {
                barChart.highlightValue(null)
            }
        })
    }

    private fun getSum(a: Int, b: Int): Float {
        for (i in a..b) {
            sum += mData.kLineDatas[i].close
        }
        return sum
    }

    private fun culcMaxscale(count: Float): Float {
        var max = 1f
        max = count / 127 * 5
        return max
    }


    private fun setData(mData: DataParse) {
        kLineDatas = mData.kLineDatas
        val size: Int = kLineDatas!!.size //点的个数
        // axisLeftBar.setAxisMaxValue(mData.getVolmax());
        val unit: String = MyUtils.getVolUnit(mData.volmax)
        var u = 1
        if ("万手" == unit) {
            u = 4
        } else if ("亿手" == unit) {
            u = 8
        }
        axisLeftBar!!.valueFormatter = VolFormatter(Math.pow(10.0, u.toDouble()).toInt())
        // axisRightBar.setAxisMaxValue(mData.getVolmax());
        Log.e("@@@", mData.volmax.toString() + "da")
        val xVals = ArrayList<String>()
        val barEntries = ArrayList<BarEntry>()
        val candleEntries = ArrayList<CandleEntry>()
        val line5Entries =
            ArrayList<Entry>()
        val line10Entries =
            ArrayList<Entry>()
        val line30Entries =
            ArrayList<Entry>()
        var i = 0
        var j = 0
        while (i < mData.kLineDatas.size) {
            xVals.add(mData.kLineDatas[i].date.toString() + "")
            barEntries.add(BarEntry(mData.kLineDatas[i].vol, i))
            candleEntries.add(
                CandleEntry(
                    i,
                    mData.kLineDatas[i].high,
                    mData.kLineDatas[i].low,
                    mData.kLineDatas[i].open,
                    mData.kLineDatas[i].close
                )
            )
            if (i >= 4) {
                sum = 0f
                line5Entries.add(Entry(getSum(i - 4, i) / 5, i))
            }
            if (i >= 9) {
                sum = 0f
                line10Entries.add(Entry(getSum(i - 9, i) / 10, i))
            }
            if (i >= 29) {
                sum = 0f
                line30Entries.add(
                    Entry(
                        getSum(i - 29, i) / 30,
                        i
                    )
                )
            }
            i++
            j++
        }
        barDataSet = BarDataSet(barEntries, "成交量")
        barDataSet?.setBarSpacePercent(50f) //bar空隙
        barDataSet?.setHighlightEnabled(true)
        barDataSet?.setHighLightAlpha(255)
        barDataSet?.setHighLightColor(Color.WHITE)
        barDataSet?.setDrawValues(false)
        barDataSet?.setColor(Color.RED)
        val barData = BarData(xVals, barDataSet)
        barChart.data = barData
        val viewPortHandlerBar = barChart.viewPortHandler
        viewPortHandlerBar.setMaximumScaleX(culcMaxscale(xVals.size.toFloat()))
        val touchmatrix = viewPortHandlerBar.matrixTouch
        val xscale = 3f
        touchmatrix.postScale(xscale, 1f)
        val candleDataSet = CandleDataSet(candleEntries, "KLine")
        candleDataSet.setDrawHorizontalHighlightIndicator(false)
        candleDataSet.isHighlightEnabled = true
        candleDataSet.highLightColor = Color.WHITE
        candleDataSet.valueTextSize = 10f
        candleDataSet.setDrawValues(false)
        candleDataSet.color = Color.RED
        candleDataSet.shadowWidth = 1f
        candleDataSet.axisDependency = YAxis.AxisDependency.LEFT
        val candleData = CandleData(xVals, candleDataSet)
        val sets = ArrayList<ILineDataSet>()
        /******此处修复如果显示的点的个数达不到MA均线的位置所有的点都从0开始计算最小值的问题 */
        if (size >= 30) {
            setMaLine(5, xVals, line5Entries)?.let { sets.add(it) }
            setMaLine(10, xVals, line10Entries)?.let { sets.add(it) }
            setMaLine(30, xVals, line30Entries)?.let { sets.add(it) }
        } else if (size >= 10 && size < 30) {
            setMaLine(5, xVals, line5Entries)?.let { sets.add(it) }
            setMaLine(10, xVals, line10Entries)?.let { sets.add(it) }
        } else if (size >= 5 && size < 10) {
            setMaLine(5, xVals, line5Entries)?.let { sets.add(it) }
        }
        val combinedData = CombinedData(xVals)
        val lineData = LineData(xVals, sets)
        combinedData.setData(candleData)
        combinedData.setData(lineData)
        combinedchart.data = combinedData
        combinedchart.moveViewToX(mData.kLineDatas.size - 1f)
        val viewPortHandlerCombin = combinedchart.viewPortHandler
        viewPortHandlerCombin.setMaximumScaleX(culcMaxscale(xVals.size.toFloat()))
        val matrixCombin = viewPortHandlerCombin.matrixTouch
        val xscaleCombin = 3f
        matrixCombin.postScale(xscaleCombin, 1f)
        combinedchart.moveViewToX(mData.kLineDatas.size - 1f)
        barChart.moveViewToX(mData.kLineDatas.size - 1f)
        setOffset()
        /****************************************************************************************
         * 此处解决方法来源于CombinedChartDemo，k线图y轴显示问题，图表滑动后才能对齐的bug，希望有人给出解决方法
         * (注：此bug现已修复，感谢和chenguang79一起研究)
         */
        handler.sendEmptyMessageDelayed(0, 300)
    }

    private fun setMaLine(
        ma: Int,
        xVals: ArrayList<String>,
        lineEntries: ArrayList<Entry>
    ): LineDataSet? {
        val lineDataSetMa = LineDataSet(lineEntries, "ma$ma")
        if (ma == 5) {
            lineDataSetMa.isHighlightEnabled = true
            lineDataSetMa.setDrawHorizontalHighlightIndicator(false)
            lineDataSetMa.highLightColor = Color.WHITE
        } else { /*此处必须得写*/
            lineDataSetMa.isHighlightEnabled = false
        }
        lineDataSetMa.setDrawValues(false)
        if (ma == 5) {
            lineDataSetMa.color = Color.GREEN
        } else if (ma == 10) {
            lineDataSetMa.color = Color.GRAY
        } else {
            lineDataSetMa.color = Color.YELLOW
        }
        lineDataSetMa.lineWidth = 1f
        lineDataSetMa.setDrawCircles(false)
        lineDataSetMa.axisDependency = YAxis.AxisDependency.LEFT
        return lineDataSetMa
    }

    /*设置量表对齐*/
    private fun setOffset() {
        val lineLeft = combinedchart.viewPortHandler.offsetLeft()
        val barLeft = barChart.viewPortHandler.offsetLeft()
        val lineRight = combinedchart.viewPortHandler.offsetRight()
        val barRight = barChart.viewPortHandler.offsetRight()
        val barBottom = barChart.viewPortHandler.offsetBottom()
        val offsetLeft: Float
        val offsetRight: Float
        var transLeft = 0f
        var transRight = 0f
        /*注：setExtraLeft...函数是针对图表相对位置计算，比如A表offLeftA=20dp,B表offLeftB=30dp,则A.setExtraLeftOffset(10),并不是30，还有注意单位转换*/if (barLeft < lineLeft) {
            /* offsetLeft = Utils.convertPixelsToDp(lineLeft - barLeft);
            barChart.setExtraLeftOffset(offsetLeft);*/
            transLeft = lineLeft
        } else {
            offsetLeft =
                Utils.convertPixelsToDp(barLeft - lineLeft)
            combinedchart.extraLeftOffset = offsetLeft
            transLeft = barLeft
        }
        /*注：setExtraRight...函数是针对图表绝对位置计算，比如A表offRightA=20dp,B表offRightB=30dp,则A.setExtraLeftOffset(30),并不是10，还有注意单位转换*/if (barRight < lineRight) {
            /*  offsetRight = Utils.convertPixelsToDp(lineRight);
            barChart.setExtraRightOffset(offsetRight);*/
            transRight = lineRight
        } else {
            offsetRight = Utils.convertPixelsToDp(barRight)
            combinedchart.extraRightOffset = offsetRight
            transRight = barRight
        }
        barChart.setViewPortOffsets(transLeft, 15f, transRight, barBottom)
    }

}
