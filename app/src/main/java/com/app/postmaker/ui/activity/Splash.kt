package com.app.postmaker.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.postmaker.R
import com.app.postmaker.util.Method
import androidx.appcompat.app.AppCompatDelegate
import android.content.Intent
import android.os.Handler
import android.os.Looper


class Splash : AppCompatActivity() {

    private lateinit var method: Method
    private var isCancelled: Boolean = false
    private var splashScreenTimeOut: Int = 1000 // splash screen timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        method = Method(this@Splash)
        when (method.pref.getString(method.themSetting, "system")) {
            "system" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        setContentView(R.layout.activity_splash)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        method.changeStatusBarColor();

        Handler(Looper.getMainLooper()).postDelayed({
            if (!isCancelled) {
                startActivity(Intent(this@Splash, MainActivity::class.java))
                finishAffinity()
            }
        }, splashScreenTimeOut.toLong())

    }

    override fun onDestroy() {
        isCancelled = true
        super.onDestroy()
    }

}