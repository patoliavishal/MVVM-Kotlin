package com.hi.mvvmkotlin.data.local.db

import com.hi.mvvmkotlin.data.model.db.User
import io.reactivex.Observable

/**
 * Created by Vishal Patel on 11/10/19.
 */
interface DbHelper {

    fun doValidateSignIn(email: String, password: String): Observable<Boolean>

    fun doIsUserExist(email: String): Observable<Int>

    fun doInsertSignUpDetails(user: User): Observable<Long>
}