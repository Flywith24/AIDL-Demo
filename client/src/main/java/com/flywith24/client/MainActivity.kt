package com.flywith24.client

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.view.View
import com.flywith24.aidl_demo.IInstallService

class MainActivity : AppCompatActivity() {
    private var iService: IInstallService? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            iService = IInstallService.Stub.asInterface(service)
            try {
                val result = iService?.silenceInstall("")
                Log.i("yyz11", "onServiceConnected: $result")
            } catch (e: RemoteException) {
                e.printStackTrace()
            }

        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i("yyz11", "onServiceDisconnected: ")
            iService = null
        }
    }

    fun click(view: View) {
        bindService(Intent("com.flywith24.action.install").apply {
            setPackage("com.flywith24.aidl_demo")
        }, connection, Context.BIND_AUTO_CREATE)
    }
}
