package com.hi.mvvmkotlin.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hi.mvvmkotlin.data.local.db.dao.UserDao
import com.hi.mvvmkotlin.data.model.db.User

/**
 * Created by Vishal Patel on 11/10/19.
 */
//Register your model as entities
@Database(
    entities = [User::class], version = 1, exportSchema = false
)

//Create Dao for your entities
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}