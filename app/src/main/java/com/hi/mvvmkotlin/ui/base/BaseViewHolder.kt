package com.hi.mvvmkotlin.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Vishal Patel on 11/10/19.
 */
abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(position: Int)
}