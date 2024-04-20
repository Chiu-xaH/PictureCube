package com.chiuxah.picturecube.ui.utils

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.chiuxah.picturecube.App

fun MyToast(text : String) {
    Handler(Looper.getMainLooper()).post{ Toast.makeText(App.context,text,Toast.LENGTH_SHORT).show() }
}