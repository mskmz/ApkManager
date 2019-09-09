package com.wzk.apkmanager.install

import android.content.Intent
import android.net.Uri
import android.util.Log
import com.wzk.apkmanager.BaseApplication
import java.io.File

object FileUtils {
    val TAG = "FileUtils>>>"

    private val mContext by lazy {
        BaseApplication.application.baseContext
    }
    private val map = HashMap<String, String>()

    init {
        map["apk"] = "application/vnd.android.package-archive"
        map["txt"] = "text/plain"
    }

    fun getRootPath(): String {
        return mContext.filesDir.absolutePath
    }

    fun getDirList(path: String): List<File> {
        val ret = ArrayList<File>()
        File(path)
            .run {
                if (!exists() || !isDirectory) {
                    null
                } else {
                    this
                }
            }?.run {
                this.listFiles()
            }?.forEach {
                ret.add(it)
            }
        return ret
    }

    fun getMINE(path: String): String {
        path.split(".")
            .run {
                if (size > 1) {
                    this[1]
                } else {
                    null
                }
            }?.run {
                Log.d(TAG, this)
                map[this]
            }?.run {
                return this
            }
        return ""
    }

    fun openFile(path: String): Boolean {
        val file = File(path)
        if (!file.exists() || !file.isFile || file.length() < 1) {
            return false
        }
        val i = Intent(Intent.ACTION_VIEW)
        i.setDataAndType(Uri.parse("file://${path}"), getMINE(path))
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        mContext.startActivity(i)
        return true
    }

    fun openFile(path: Uri, name: String): Boolean {
        val i = Intent(Intent.ACTION_VIEW)
        i.setDataAndType(path, getMINE(name))
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        mContext.startActivity(i)
        return true
    }
}