package com.chiuxah.picturecube

import android.app.Application
import android.content.Context
import androidx.compose.ui.unit.dp

class App : Application() {

    companion object {
        lateinit var context : Context
        val Blur = 20.dp
    }
    override fun onCreate() {
        context = applicationContext
        super.onCreate()
    }
}