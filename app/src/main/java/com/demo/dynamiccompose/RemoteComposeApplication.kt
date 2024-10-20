package com.demo.dynamiccompose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RemoteComposeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}