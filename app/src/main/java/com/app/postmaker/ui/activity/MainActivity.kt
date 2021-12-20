package com.app.postmaker.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.app.postmaker.R
import com.app.postmaker.ui.fragment.HomeFragment
import com.app.postmaker.ui.fragment.MyWorkFragment
import com.app.postmaker.ui.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNav)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frameLayout_main,
                HomeFragment(),
                getString(R.string.home)
            )
            .commitAllowingStateLoss()

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.frameLayout_main,
                        HomeFragment(),
                        getString(R.string.home)
                    )
                    .commitAllowingStateLoss()
                R.id.nav_work -> supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.frameLayout_main,
                        MyWorkFragment(),
                        getString(R.string.home)
                    )
                    .commitAllowingStateLoss()
                R.id.nav_pro -> supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.frameLayout_main,
                        ProfileFragment(),
                        getString(R.string.home)
                    )
                    .commitAllowingStateLoss()
            }
            false // return true;
        }
    }

    /* override fun onCreateOptionsMenu(menu: Menu): Boolean {
         // Inflate the menu; this adds items to the action bar if it is present.
         menuInflater.inflate(R.menu.main, menu)
         return true
     }*/

}