package com.app.postmaker.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.postmaker.R
import com.app.postmaker.ui.fragment.HomeFragment
import com.app.postmaker.ui.fragment.MyWorkFragment
import com.app.postmaker.ui.fragment.ProfileFragment
import com.app.postmaker.ui.fragment.SettingFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNav)
        toolbar = findViewById(R.id.toolbar_main)

        toolbar.title = resources.getString(R.string.app_name)
        setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frameLayout_main,
                HomeFragment(),
                getString(R.string.home)
            )
            .commitAllowingStateLoss()

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    selectDrawerItem(0)
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frameLayout_main,
                            HomeFragment(),
                            getString(R.string.home)
                        )
                        .commitAllowingStateLoss()
                }
                R.id.nav_work -> {
                    selectDrawerItem(1)
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frameLayout_main,
                            MyWorkFragment(),
                            getString(R.string.work)
                        )
                        .commitAllowingStateLoss()
                }
                R.id.nav_pro -> {
                    selectDrawerItem(2)
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frameLayout_main,
                            ProfileFragment(),
                            getString(R.string.profile)
                        )
                        .commitAllowingStateLoss()
                }
                R.id.nav_setting -> {
                    selectDrawerItem(3)
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frameLayout_main,
                            SettingFragment(),
                            getString(R.string.setting)
                        )
                        .commitAllowingStateLoss()
                }
            }
            false // return true;
        }
    }

    private fun selectDrawerItem(position: Int) {
        bottomNavigationView.menu.getItem(position).isChecked = true
    }

    private fun deselectDrawerItem(position: Int) {
        bottomNavigationView.menu.getItem(position).isCheckable = false
        bottomNavigationView.menu.getItem(position).isChecked = false
    }

}