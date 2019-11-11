package com.hi.mvvmkotlin.ui.fakedata

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.hi.mvvmkotlin.R
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
open class FakeDataListActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(FakeDataListActivity::class.java)

    @Test
    open fun fakeListItemViewScrollTest() {
        val recyclerView: RecyclerView =
            mActivityTestRule.activity.findViewById(R.id.fakeDataRVList)
        val itemCount = recyclerView.adapter!!.itemCount
        onView(withId(R.id.fakeDataRVList))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(mActivityTestRule.activity.window.decorView)
                )
            )
            .perform(
                RecyclerViewActions.scrollToPosition<ViewHolder?>(
                    itemCount - 1
                )
            )
    }
}