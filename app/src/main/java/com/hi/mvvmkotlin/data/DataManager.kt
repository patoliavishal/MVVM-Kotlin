package com.hi.mvvmkotlin.data

import com.hi.mvvmkotlin.data.local.db.DbHelper
import com.hi.mvvmkotlin.data.remote.ApiHelper

/**
 * Created by Vishal Patel on 11/10/19.
 */
interface DataManager : ApiHelper, DbHelper