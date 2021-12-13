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
    @PrimaryKey(autoGenerate = true) val id: Int? = 0
) : Serializable {

    /*@PrimaryKey(autoGenerate = true)
    private var id = 0

    @ColumnInfo(name = "name")
    private var name: String? = null

    @ColumnInfo(name = "email")
    private var email: String? = null

    @ColumnInfo(name = "phoneNo")
    private var phoneNo: String? = null

    @ColumnInfo(name = "webSite")
    private var webSite: String? = null

    @ColumnInfo(name = "address")
    private var address: String? = null*/

}