package com.hi.mvvmkotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hi.mvvmkotlin.data.DataManager
import com.hi.mvvmkotlin.ui.fakedata.FakeDataViewModel
import com.hi.mvvmkotlin.ui.signin.SignInViewModel
import com.hi.mvvmkotlin.ui.signup.SignUpViewModel
import com.hi.mvvmkotlin.utils.rx.SchedulerProvider
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Vishal Patel on 11/10/19.
 */
@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelProviderFactory @Inject
constructor(
    private val dataManager: DataManager,
    private val schedulerProvider: SchedulerProvider
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FakeDataViewModel::class.java) -> FakeDataViewModel(
                dataManager,
                schedulerProvider
            ) as T
            modelClass.isAssignableFrom(SignInViewModel::class.java) -> SignInViewModel(
                dataManager,
                schedulerProvider
            ) as T
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> SignUpViewModel(
                dataManager,
                schedulerProvider
            ) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
