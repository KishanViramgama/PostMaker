package com.app.postmaker.util

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


class Method(var activity: Activity) {

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

}