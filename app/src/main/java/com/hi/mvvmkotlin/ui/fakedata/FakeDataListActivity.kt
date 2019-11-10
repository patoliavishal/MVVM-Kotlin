package com.hi.mvvmkotlin.ui.fakedata

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.hi.mvvmkotlin.BR
import com.hi.mvvmkotlin.R
import com.hi.mvvmkotlin.ViewModelProviderFactory
import com.hi.mvvmkotlin.data.model.api.FakeDataBean
import com.hi.mvvmkotlin.databinding.ActivityFakedataListBinding
import com.hi.mvvmkotlin.ui.base.BaseActivity
import com.hi.mvvmkotlin.utils.NetworkUtils
import javax.inject.Inject

/**
 * Created by Vishal Patel on 11/10/19.
 */
@Suppress("UNCHECKED_CAST")
class FakeDataListActivity : BaseActivity<ActivityFakedataListBinding, FakeDataViewModel>(),
    FakeDataNavigator {

    @Inject
    lateinit var mFakeDataAdapter: FakeDataAdapter
    @Inject
    lateinit var factory: ViewModelProviderFactory

    private var mActivityFakeDataListBinding: ActivityFakedataListBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_fakedata_list

    override val viewModel: FakeDataViewModel
        get() = ViewModelProviders.of(this, factory).get(FakeDataViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        mActivityFakeDataListBinding = getViewDataBinding()
        viewModel.navigator = this

        //Fire API to get Fake Data List
        if (NetworkUtils.isNetworkConnected(this)) {
            viewModel.fakeDataListAPICall()
        } else {
            noInternetConnection()
        }

        //Set Toolbar Back Press
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        //Bind RecyclerView with Adapter
        mActivityFakeDataListBinding?.fakeDataRVList?.itemAnimator = DefaultItemAnimator()
        mActivityFakeDataListBinding?.fakeDataRVList?.adapter = mFakeDataAdapter

        viewModel.fakeDataLiveData.observe(this,
            Observer<List<FakeDataBean>> { unit ->
                if (unit.isNotEmpty()) {
                    mFakeDataAdapter.addItems(unit as List<FakeDataBean>)
                }
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun noInternetConnection() {
        viewModel.setIsLoading(false)
    }

    override fun handleError(throwable: Throwable) {
        viewModel.setIsLoading(false)
    }

    override fun onBackPressed() {
        finish()
    }
}
