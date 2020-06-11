package com.example.szh.tools

import android.content.Context
import android.widget.Toast

class MyToast {
    fun makeToast(context: Context, txt: String) {
        Toast.makeText(context,txt,Toast.LENGTH_LONG).show()
    }
}