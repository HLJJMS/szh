package com.example.szh.tools

import android.content.Context

class Loadding {

    companion object {
        var loadView: LoaddingView? = null
        fun showLoadView(context: Context) {
            if (null == loadView) {
                loadView = LoaddingView(context)
                loadView?.show()
            } else {
                loadView?.show()
            }
        }

        fun hideLoadView(context: Context) {
            if (null == loadView) {
                loadView = LoaddingView(context)
                loadView?.dismiss()
            } else {
                loadView?.dismiss()
            }
        }
        fun setLoadView(context: Context){
            loadView = LoaddingView(context)
        }
    }
}