package com.hi.mvvmkotlin.ui.fakedata

import androidx.lifecycle.MutableLiveData
import com.hi.mvvmkotlin.MVVMKotlin
import com.hi.mvvmkotlin.R
import com.hi.mvvmkotlin.data.DataManager
import com.hi.mvvmkotlin.data.model.api.FakeDataBean
import com.hi.mvvmkotlin.data.remote.AppApiHelper.Companion.apiService
import com.hi.mvvmkotlin.ui.base.BaseViewModel
import com.hi.mvvmkotlin.utils.rx.SchedulerProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Vishal Patel on 11/10/19.
 */
class FakeDataViewModel(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<FakeDataNavigator>(dataManager, schedulerProvider) {

    val fakeDataLiveData: MutableLiveData<ArrayList<FakeDataBean>> = MutableLiveData()

    fun fakeDataListAPICall() {
        setIsLoading(true)
        apiService.doFetchFakeDataListAsync()?.enqueue(object :
            Callback<List<FakeDataBean>> {
            override fun onResponse(
                call: Call<List<FakeDataBean>>,
                response: Response<List<FakeDataBean>>
            ) {
                if (response.isSuccessful) {
                    setIsLoading(false)
                    fakeDataLiveData.value = response.body() as ArrayList<FakeDataBean>?
                }
            }

            override fun onFailure(call: Call<List<FakeDataBean>>, t: Throwable) {
                setIsLoading(false)
                navigator?.handleError(throwable = Throwable(MVVMKotlin.getInstance().getString(R.string.no_data_found)))
            }
        })
    }
}