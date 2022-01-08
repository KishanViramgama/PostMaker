package com.app.postmaker.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.postmaker.R
import com.google.android.material.appbar.MaterialToolbar

class AboutUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_about_us)
        toolbar.title = resources.getString(R.string.about_us)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}