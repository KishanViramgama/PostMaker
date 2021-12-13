package com.app.postmaker.database

import androidx.room.Dao
import androidx.room.Insert
import com.app.postmaker.item.Profile

@Dao
interface DataEntity {

    @Insert
    fun insert(vararg profile: Profile)

}