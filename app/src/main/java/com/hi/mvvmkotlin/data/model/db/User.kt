package com.hi.mvvmkotlin.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

/**
 * Created by Vishal Patel on 11/10/19.
 */
@Entity(tableName = "tbl_user")
class User {

    @NotNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userid")
    var userid: Int = 0

    @NotNull
    @ColumnInfo(name = "username")
    var username: String? = null

    @NotNull
    @ColumnInfo(name = "email")
    var email: String? = null

    @NotNull
    @ColumnInfo(name = "password")
    var password: String? = null
}