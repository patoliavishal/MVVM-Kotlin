package com.hi.mvvmkotlin.ui.base

import android.Manifest
import android.R
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.NetworkUtils
import com.hi.mvvmkotlin.MVVMKotlin
import com.hi.mvvmkotlin.utils.AppConstants.PERMISSION_REQUEST_CODE
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection

/**
 * Created by Vishal Patel on 11/10/19.
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity(),
    BaseFragment.Callback {

    private var viewDataBinding: Any? = null
    private var mViewModel: V? = null
    private var progressDialog: ProgressDialog? = null

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set ui model
     *
     * @return ui model instance
     */
    abstract val viewModel: V

    val isNetworkConnected: Boolean
        get() = NetworkUtils.isConnected()

    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    fun getViewDataBinding(): T {
        return (this.viewDataBinding as T?)!!
    }

    @SuppressLint("ObsoleteSdkInt")
    @TargetApi(VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        fun addSpace(str: String, size: Int): String {
            val str1 = StringBuilder()
            for (j in 0 until size - str.length) {
                str1.append(" ")
            }
            str1.append(str)
            return str1.toString()
        }

        fun requestPermission(activity: Activity) {

            if (ContextCompat.checkSelfPermission(
                    MVVMKotlin.getInstance().applicationContext,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                ) {
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        PERMISSION_REQUEST_CODE
                    )
                } else {
                    onSnackBar(
                        activity.findViewById(R.id.content)!!,
                        "Enable permission from app settings."
                    )
                }
            }
        }

        fun checkPermission(activity: Activity): Boolean {
            val result = ContextCompat.checkSelfPermission(
                activity.applicationContext!!,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            return result == PackageManager.PERMISSION_GRANTED
        }

        fun onSnackBar(view: View, msg: String) {
            val snackbar = Snackbar.make(
                view, msg,
                Snackbar.LENGTH_LONG
            )
            val snackbarView = snackbar.view
            snackbarView.setBackgroundColor(Color.WHITE)
            val textView =
                snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
            textView.setTextColor(
                ContextCompat.getColor(
                    MVVMKotlin.getInstance(),
                    com.hi.mvvmkotlin.R.color.colorAccent
                )
            )
            textView.textSize = 16f
            snackbar.show()
        }
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    @TargetApi(VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    private fun performDataBinding() {
        this.viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        this.mViewModel = if (mViewModel == null) viewModel else mViewModel
        if (mViewModel == null) getViewDataBinding().setVariable(
            bindingVariable,
            viewModel
        ) else getViewDataBinding().setVariable(bindingVariable, mViewModel)
        getViewDataBinding().setVariable(bindingVariable, viewModel)
        getViewDataBinding().executePendingBindings()
    }
}
