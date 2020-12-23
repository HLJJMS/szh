package com.diwaves.news.tools;

import android.util.SparseArray;

import com.diwaves.news.bean.KLineBean;
import com.diwaves.news.bean.MyKLineBean;

import java.util.ArrayList;

public class DataParse {
    private ArrayList<KLineBean> kDatas = new ArrayList<>();
    private float baseValue;
    private float permaxmin;
    private float volmax;
    private SparseArray<String> dayLabels;
    private String code = "sz002081";
    private int decreasingColor;
    private int increasingColor;
    private String stockExchange;
    private SparseArray<String> xValuesLabel = new SparseArray<>();
    private int firstDay = 10;


    public void parseKLine(MyKLineBean bean) {
        for (int i = 0; i < bean.date.size(); i++) {
            KLineBean kLineData = new KLineBean();
            kLineData.date = bean.getDate().get(i);
            kLineData.open = bean.getOpen().get(i);
            kLineData.close = bean.getClose().get(i);
            kLineData.high = bean.getHigh().get(i);
            kLineData.low = bean.getLow().get(i);
            kLineData.vol = bean.getVolume().get(i);
            xValuesLabel.put(i, kLineData.date);
            kDatas.add(kLineData);
        }
    }

    public float getMin() {
        return baseValue - permaxmin;
    }

    public float getMax() {
        return baseValue + permaxmin;
    }

    public float getPercentMax() {
        return permaxmin / baseValue;
    }

    public float getPercentMin() {
        return -getPercentMax();
    }


    public float getVolmax() {
        return volmax;
    }

    public ArrayList<KLineBean> getKLineDatas() {
        return kDatas;
    }

    public SparseArray<String> getXValuesLabel() {
        return xValuesLabel;
    }
}
