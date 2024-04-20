package com.chiuxah.picturecube.logic.utils

import android.content.Context
import android.preference.PreferenceManager
import com.chiuxah.picturecube.App

object SharePrefs {
    val prefs = App.context.getSharedPreferences("com.hfut.schedule_preferences", Context.MODE_PRIVATE)

    fun Save(title : String,info : String?) {
        val saved = PreferenceManager.getDefaultSharedPreferences(App.context)
        if (saved.getString(title, "") != info) { saved.edit().putString(title,info).apply() }
    }

    fun SaveBoolean(title : String,default : Boolean,save : Boolean) {
        val saved = PreferenceManager.getDefaultSharedPreferences(App.context)
        if (saved.getBoolean(title, default) != save) { saved.edit().putBoolean(title,save).apply() }
    }

    fun SaveInt(title : String,save : Int) {
        val saved = PreferenceManager.getDefaultSharedPreferences(App.context)
        if (saved.getInt(title, 0) != save) { saved.edit().putInt(title,save).apply() }
    }
}