package com.app.postmaker.ui.activity

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.app.postmaker.R
import com.app.postmaker.ui.fragment.HomeFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout_main, HomeFragment(), getString(R.string.home))
            .commitAllowingStateLoss()

        Toast.makeText(this,"load activity",Toast.LENGTH_SHORT).show()


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

}