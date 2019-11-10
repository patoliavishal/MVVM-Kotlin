package com.hi.mvvmkotlin.data

import com.hi.mvvmkotlin.data.local.db.DbHelper
import com.hi.mvvmkotlin.data.model.api.FakeDataBean
import com.hi.mvvmkotlin.data.model.db.User
import com.hi.mvvmkotlin.data.remote.ApiHelper
import io.reactivex.Observable
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Vishal Patel on 11/10/19.
 */
@Singleton
internal class AppDataManager @Inject
constructor(private val mApiHelper: ApiHelper, private val mDbHelper: DbHelper) : DataManager {

    override fun doFetchFakeDataListAsync(): Call<List<FakeDataBean>>? {
        return mApiHelper.doFetchFakeDataListAsync()
    }

    override fun doIsUserExist(email: String): Observable<Int> {
        return mDbHelper.doIsUserExist(email)
    }

    override fun doValidateSignIn(email: String, password: String): Observable<Boolean> {
        return mDbHelper.doValidateSignIn(email, password)
    }

    override fun doInsertSignUpDetails(user: User): Observable<Long> {
        return mDbHelper.doInsertSignUpDetails(user)
    }

}