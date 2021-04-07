package com.example.mywaterapp

import android.app.Application
import android.util.Log
import com.example.mywaterapp.data.WaterDatabase
import com.jakewharton.threetenabp.AndroidThreeTen
import timber.log.Timber

class ControlApp: Application() {

    companion object{
        private const val TAG = "ControlApp"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
        AndroidThreeTen.init(this)
        WaterDatabase.init(context = this)
        if (BuildConfig.DEBUG){
        Timber.plant(Timber.DebugTree())
        }
    }
}