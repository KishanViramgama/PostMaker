package com.app.postmaker.database

import android.annotation.SuppressLint
import android.app.Activity
import androidx.room.Room
import androidx.room.RoomDatabase

class DatabaseClient private constructor(activity: Activity) {
    //our app database object
    val appDatabase: AppDatabase =
        Room.databaseBuilder(activity, AppDatabase::class.java, "UserData")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var mInstance: DatabaseClient? = null

        @Synchronized
        fun getInstance(activity: Activity): DatabaseClient? {
            if (mInstance == null) {
                mInstance = DatabaseClient(activity)
            }
            return mInstance
        }
    }

}