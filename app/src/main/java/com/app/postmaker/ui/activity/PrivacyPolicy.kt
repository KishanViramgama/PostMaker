package com.app.postmaker.ui.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.app.postmaker.R
import com.app.postmaker.util.Method
import com.google.android.material.appbar.MaterialToolbar
import java.io.IOException
import java.io.InputStream


class PrivacyPolicy : AppCompatActivity() {

    private lateinit var method: Method

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)

        method = Method(this)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_privacy_policy)
        toolbar.title = resources.getString(R.string.privacy_policy)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val webView: WebView = findViewById(R.id.webView_privacy_policy)

        val str: String = try {
            val `is`: InputStream = assets.open("privarcypolicy.txt")
            val size: Int = `is`.available()
            val buffer = ByteArray(size) // Read the entire asset into a local byte buffer.
            `is`.read(buffer)
            `is`.close()
            String(buffer) // Convert the buffer into a string.
        } catch (e: IOException) {
            throw RuntimeException(e) // Should never happen!
        }

        webView.setBackgroundColor(Color.TRANSPARENT)
        webView.isFocusableInTouchMode = false
        webView.isFocusable = false
        webView.settings.defaultTextEncodingName = "UTF-8"
        webView.settings.javaScriptEnabled = true
        val mimeType = "text/html"
        val encoding = "utf-8"

        val text = ("<html><head>"
                + "<style type=\"text/css\">@font-face {font-family: MyFont;src: url(\"file:///android_asset/font/latoregular.ttf\")}body{font-family: MyFont;color: " + method.webViewText() + "}"
                + "</style></head>"
                + "<body>"
                + str
                + "</body></html>")

        webView.loadDataWithBaseURL(null, text, mimeType, encoding, null)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}