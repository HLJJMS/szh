package com.example.szh.network

import android.app.Application
import android.content.Context
import com.example.szh.tools.Loadding
import com.jess.arms.base.delegate.AppLifecycles

class AppLifecyclesImpl() : AppLifecycles {
    override fun attachBaseContext(base: Context) {

    }

    override fun onCreate(application: Application) {
        Loadding.setLoadView(application)
    }

    override fun onTerminate(application: Application) {

    }
}