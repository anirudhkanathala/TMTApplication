package com.example.tmtapplication.application

import android.app.Application
import com.example.tmtapplication.BuildConfig
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

/**
 * <h1>TMTApplication</h1>
 * TMTApplication is used to initialize components/library
 */

@HiltAndroidApp
class TMTApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)
    }
}
