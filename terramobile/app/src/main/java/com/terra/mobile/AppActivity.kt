package com.terra.mobile

import android.app.Application
import com.terra.mobile.data.AppContainer
import com.terra.mobile.data.DefaultAppContainer


class AppActivity : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
