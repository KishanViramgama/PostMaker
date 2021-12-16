package com.app.postmaker.item

import java.io.Serializable
import androidx.room.ColumnInfo
import androidx.room.Entity

import androidx.room.PrimaryKey

@Entity
data class Profile(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "phoneNo") val phoneNo: String,
    @ColumnInfo(name = "webSite") val webSite: String,
    @ColumnInfo(name = "address") val address: String,
    @PrimaryKey(autoGenerate = true) val id: Int?
) : Serializable {

}