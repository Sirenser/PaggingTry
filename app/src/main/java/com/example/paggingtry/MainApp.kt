package com.example.paggingtry

import android.app.Application
import com.example.paggingtry.di.AppComponent
import com.example.paggingtry.di.DaggerAppComponent


class MainApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()

    }


}