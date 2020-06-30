package com.flywith24.aidl_demo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * @author Flywith24
 * @date   2020/6/30
 * time   16:43
 * description
 */
class AIDLService : Service() {
    override fun onBind(intent: Intent?): IBinder? = stub
    private val stub = object : IInstallService.Stub() {
        override fun silenceInstall(path: String?): Int {
            Log.i("yyz11", "silenceInstall: 执行静默安装")
            return -1
        }
    }
}