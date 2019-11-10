package com.hi.mvvmkotlin.ui.fakedata

import androidx.databinding.ObservableField
import com.hi.mvvmkotlin.data.model.api.FakeDataBean

/**
 * Created by Vishal Patel on 11/10/19.
 */
class FakeDataItemViewModel(
    private val fakeDataBean: FakeDataBean,
    var listener: FakeDataViewModelListener
) {

    var id: ObservableField<String> = ObservableField(fakeDataBean.id)
    var title: ObservableField<String> = ObservableField(fakeDataBean.title)
    var url: ObservableField<String> = ObservableField(fakeDataBean.url)
    var thumbnail: ObservableField<String> = ObservableField(fakeDataBean.thumbnailUrl)

    fun onItemClick() {
        listener.onItemClick(fakeDataBean.title)
    }

    interface FakeDataViewModelListener {

        fun onItemClick(blogUrl: String)
    }
}
