package com.app.postmaker.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.postmaker.item.ProfileList

@Dao
interface DataEntity {
    @Insert
    fun insert(profileList: ProfileList?)

    @Query("SELECT * FROM ProfileList")
    fun userData(): List<ProfileList?>?
}