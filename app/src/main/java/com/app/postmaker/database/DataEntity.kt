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

    @Update
    fun update(vararg profile: Profile)

    @Query("SELECT EXISTS(SELECT * FROM Profile WHERE id = :id)")
    fun isRowIsExist(id: Int): Boolean

    @Query("SELECT * FROM Profile")
    fun getData(): Profile
}