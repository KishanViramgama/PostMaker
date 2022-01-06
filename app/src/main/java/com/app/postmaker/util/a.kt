package com.app.postmaker.util

import android.app.Activity

import android.content.SharedPreferences


internal class a(var activity: Activity) {
    var pref: SharedPreferences
    var editor: SharedPreferences.Editor
    private val myPreference = "book_app"
    var themSetting = "them"

    init {
        pref = activity.getSharedPreferences(myPreference, 0) // 0 - for private mode
        editor = pref.edit()
    }
}