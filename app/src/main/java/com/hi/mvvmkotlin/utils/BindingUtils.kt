package com.hi.mvvmkotlin.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hi.mvvmkotlin.data.model.api.FakeDataBean
import com.hi.mvvmkotlin.ui.fakedata.FakeDataAdapter

/**
 * Created by Vishal Patel on 11/10/19.
 */
object BindingUtils {

    @JvmStatic
    @BindingAdapter("adapter")
    fun addFakeItems(recyclerView: RecyclerView?, fakeDataList: ArrayList<FakeDataBean>?) {

        val adapter = recyclerView?.adapter as FakeDataAdapter?
        if (adapter != null) {
            adapter.clearItems()
            fakeDataList?.let { adapter.addItems(it) }
        }
    }
}
