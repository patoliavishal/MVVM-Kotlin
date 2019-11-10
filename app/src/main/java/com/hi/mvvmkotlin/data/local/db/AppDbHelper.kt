package com.hi.mvvmkotlin.data.local.db

import androidx.sqlite.db.SimpleSQLiteQuery
import com.hi.mvvmkotlin.data.model.db.User
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Vishal Patel on 11/10/19.
 */
@Singleton
class AppDbHelper @Inject
constructor(private val mAppDatabase: AppDatabase) :
    DbHelper {

    /**
     * Validate a sign in data
     */
    override fun doValidateSignIn(email: String, password: String): Observable<Boolean> {
        return Observable.fromCallable {
            mAppDatabase.userDao().validateSignIn(email, password)
        }
    }

    /**
     *  Check if user already exist or not
     */
    override fun doIsUserExist(email: String): Observable<Int> {
        return Observable.fromCallable {
            mAppDatabase.userDao()
                .isExist(SimpleSQLiteQuery("SELECT * FROM tbl_user WHERE email = '$email'"))
        }
    }

    /**
     * Insert SIGN IN details to database
     */
    override fun doInsertSignUpDetails(
        user: User
    ): Observable<Long> {
        return Observable.fromCallable {
            mAppDatabase.userDao().insertEntity(user)
        }
    }
}