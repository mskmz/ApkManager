package com.wzk.apkmanager

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.FileProvider.getUriForFile
import com.wzk.apkmanager.install.FileUtils
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instalForOpenFile()
    }

    //使用openFile的方式进行安装
    private fun instalForOpenFile() {
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
