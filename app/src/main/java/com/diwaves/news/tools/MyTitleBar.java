package com.diwaves.news.tools;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.diwaves.news.R;

public class MyTitleBar extends LinearLayout {
    ImageView iv_back, iv_end;
    TextView leftText, centerText, endText;

    public MyTitleBar(Context context) {
        super(context);
    }

    public MyTitleBar(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_bar, this, true);
        iv_back = findViewById(R.id.iv_back);
        leftText = findViewById(R.id.tv_start_txt);
        centerText = findViewById(R.id.tv_center_txt);
        endText = findViewById(R.id.tv_end_txt);
        iv_end = findViewById(R.id.iv_end);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTitleBar);
        leftText.setText(typedArray.getString(R.styleable.MyTitleBar_star_txt));
        leftText.setTextColor(typedArray.getColor(R.styleable.MyTitleBar_start_txt_color, Color.BLACK));
        centerText.setText(typedArray.getString(R.styleable.MyTitleBar_center_txt));
        endText.setText(typedArray.getString(R.styleable.MyTitleBar_end_txt));
        endText.setTextColor(typedArray.getColor(R.styleable.MyTitleBar_end_txt_color, Color.BLACK));
        iv_end.setImageDrawable(typedArray.getDrawable(R.styleable.MyTitleBar_end_img));
        typedArray.recycle();
    }

    public MyTitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyTitleBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setBackClick(OnClickListener onClickListener) {
        iv_back.setOnClickListener(onClickListener);
    }

    public void setEndTextClick(OnClickListener onClickListener) {
        endText.setOnClickListener(onClickListener);
    }

    public void setLeftText(String txt) {
        leftText.setText(txt);
    }

    public void setCenterText(String txt) {
        centerText.setText(txt);
    }

    public void setEndText(String txt) {
        endText.setText(txt);
    }


}
