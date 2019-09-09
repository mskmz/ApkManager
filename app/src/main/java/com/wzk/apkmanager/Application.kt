package com.wzk.apkmanager

import android.app.Application

class BaseApplication : Application() {
    companion object {
        lateinit var application: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}