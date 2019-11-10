package com.hi.mvvmkotlin.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.hi.mvvmkotlin.data.model.db.User
import com.hi.mvvmkotlin.ui.base.BaseDao

/**
 * Created by Vishal Patel on 11/10/19.
 */
@Dao
interface UserDao : BaseDao<User> {

    /**
     * Validate SignIn Details if match then user SignIn Otherwise Fail
     */
    @Query("select * FROM tbl_user WHERE email = :email AND password = :password")
    fun validateSignIn(email: String, password: String): Boolean
}