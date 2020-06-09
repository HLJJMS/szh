package com.example.szh.tools;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.szh.R;

public class MyTitleBar extends LinearLayout {
    ImageView iv_back;
    TextView leftText, centerText;

    public MyTitleBar(Context context) {
        super(context);
    }

    public MyTitleBar(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_bar, this, true);
        iv_back = findViewById(R.id.iv_back);
        leftText = findViewById(R.id.tv_start_txt);
        centerText = findViewById(R.id.tv_center_txt);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTitleBar);
        leftText.setText(typedArray.getString(R.styleable.MyTitleBar_star_txt));
        centerText.setText(typedArray.getString(R.styleable.MyTitleBar_center_txt));
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

    public void setLeftText(String txt) {
        leftText.setText(txt);
    }

    public void setCenterText(String txt) {
        centerText.setText(txt);
    }

}
