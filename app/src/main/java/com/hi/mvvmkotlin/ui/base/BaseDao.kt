package com.hi.mvvmkotlin.ui.base

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery

/**
 * Created by Vishal Patel on 11/10/19.
 */
@Dao
interface BaseDao<T> {

    @Delete
    fun deleteEntity(aEntity: T): Int

    @RawQuery
    fun isExist(aSimpleSQLiteQuery: SimpleSQLiteQuery): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEntity(aEntity: T): Long?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateEntity(aEntity: T): Int
}
