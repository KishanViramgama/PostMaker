package com.app.postmaker.database

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.room.Room

class DatabaseClient private constructor(private val activity: Activity) {
    //our app database object
    val appDatabase: AppDatabase =
        Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "User")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var mInstance: DatabaseClient? = null

        @Synchronized
        fun getInstance(activity: Activity): DatabaseClient? {
            try {
                if (mInstance == null) {
                    mInstance = DatabaseClient(activity)
                }
            } catch (e: Exception) {
                Log.d("error_data", e.toString())
            }
            return mInstance
        }
    }

}