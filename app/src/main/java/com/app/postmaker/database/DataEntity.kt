package com.app.postmaker.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.app.postmaker.item.Profile

@Dao
interface DataEntity {

    @Insert
    fun insert(vararg profile: Profile)

    @Update()
    fun update(vararg profile: Profile)

    @Query("SELECT * FROM Profile")
    fun getData(): Array<Profile>

}