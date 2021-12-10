package com.app.postmaker.item

import java.io.Serializable
import androidx.room.ColumnInfo
import androidx.room.Entity

import androidx.room.PrimaryKey

@Entity
class ProfileList(
    val name: String,
    val email: String,
    val phoneNo: String,
    val webSite: String,
    val address: String, @PrimaryKey(autoGenerate = true) val id: Int? = 0
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