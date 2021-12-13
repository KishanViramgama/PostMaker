package com.app.postmaker.database

import android.annotation.SuppressLint
import android.app.Activity
import androidx.room.Room

class DatabaseClient(private val activity: Activity) {
    //our app database object
    val appDatabase: AppDatabase = Room.databaseBuilder(activity, AppDatabase::class.java, "Quiz").build()

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

    init {

        //creating the app database with Room database builder
        //MyToDos is the name of the database
    }
}