package com.fivesol.kapsulemcqs

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application() {

//    @Inject
//    lateinit var adsManagement: AdsManagement
    override fun onCreate() {
        super.onCreate()
//        adsManagement.init()
    }
}