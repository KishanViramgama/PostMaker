package com.app.postmaker.util

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.view.Display
import android.view.WindowManager


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

}