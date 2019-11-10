package com.hi.mvvmkotlin.ui.fakedata

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hi.mvvmkotlin.data.model.api.FakeDataBean
import com.hi.mvvmkotlin.databinding.ItemFakedataBinding
import com.hi.mvvmkotlin.ui.base.BaseViewHolder
import javax.inject.Inject

/**
 * Created by Vishal Patel on 11/10/19.
 */
class FakeDataAdapter @Inject constructor() :
    RecyclerView.Adapter<BaseViewHolder>() {

    var fakeDataList: ArrayList<FakeDataBean> = ArrayList()

    override fun getItemCount(): Int {
        return fakeDataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return FakeDataViewHolder(
            ItemFakedataBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addItems(repoList: List<FakeDataBean>) {
        fakeDataList.addAll(repoList)
        notifyDataSetChanged()
    }

    fun clearItems() {
        fakeDataList.clear()
    }

    inner class FakeDataViewHolder(private val mBinding: ItemFakedataBinding) :
        BaseViewHolder(mBinding.root), FakeDataItemViewModel.FakeDataViewModelListener {

        override fun onBind(position: Int) {
            mBinding.viewModel = FakeDataItemViewModel(fakeDataList[position], this)
            mBinding.executePendingBindings()
        }

        override fun onItemClick(blogUrl: String) {
            //TODO Handle your item click here
        }
    }
}