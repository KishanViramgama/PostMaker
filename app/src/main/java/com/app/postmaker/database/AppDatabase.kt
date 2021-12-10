package com.app.postmaker.database

import androidx.room.Database
import com.app.postmaker.item.ProfileList
import androidx.room.RoomDatabase
import com.app.postmaker.database.DataEntity

@Database(entities = [ProfileList::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userTask(): DataEntity?
}