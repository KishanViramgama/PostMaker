package com.app.postmaker.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.graphics.Point
import android.util.Log
import android.view.Display
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.app.postmaker.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.content.SharedPreferences
import android.content.res.Configuration
import android.graphics.Color
import android.view.Window

class Method(var activity: Activity) {

    var pref: SharedPreferences
    var editor: SharedPreferences.Editor
    private val myPreference = "PostMaker"
    var themSetting = "them"

    init {
        pref = activity.getSharedPreferences(myPreference, 0) // 0 - for private mode
        editor = pref.edit()
    }

    fun changeStatusBarColor() {
        val window: Window = activity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }

    //get screen width
    fun getScreenWidth(): Int {
        val columnWidth: Int
        val wm = activity
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display: Display = wm.defaultDisplay
        val point = Point()
        point.x = display.width
        point.y = display.height
        columnWidth = point.x
        return columnWidth
    }

    //alert message box
    fun alertBox(message: String?) {
        try {
            if (!activity.isFinishing) {
                val builder = MaterialAlertDialogBuilder(activity, R.style.DialogTitleTextStyle)
                builder.setMessage(message)
                builder.setCancelable(false)
                builder.setPositiveButton(
                    activity.resources.getString(R.string.ok)
                ) { _: DialogInterface?, _: Int -> }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()
            }
        } catch (e: Exception) {
            Log.d("error_message", e.toString())
        }
    }

    //check dark mode or not
    fun isDarkMode(): Boolean {
        return when (activity.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> false // Night mode is not active, we're using the light theme
            Configuration.UI_MODE_NIGHT_YES -> true // Night mode is active, we're using dark theme

            else -> false
        }
    }

}