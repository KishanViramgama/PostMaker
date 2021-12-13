package com.app.postmaker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.postmaker.item.Profile

@Database(entities = [Profile::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userTask(): DataEntity?
}