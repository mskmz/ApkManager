package com.wzk.apkmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wzk.apkmanager.install.FileUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        installForOpenFile()
    }

    //使用openFile的方式进行安装
    private fun installForOpenFile() {
        FileUtils.run {
            getDirList(getRootPath())
                .forEach {
                    if (it.name.contains(".apk")) {
                        openApk(it)
                    }
                }
        }
    }
}
