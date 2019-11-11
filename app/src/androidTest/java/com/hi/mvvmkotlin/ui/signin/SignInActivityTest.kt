package com.hi.mvvmkotlin.ui.signin

import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.hi.mvvmkotlin.R
import junit.framework.Assert.assertTrue
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Vishal Patel on 11/11/19.
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
open class SignInActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SignInActivity::class.java)

    @Test
    fun invalidEmail() {
        onView(withId(R.id.signInEdtEmail)).perform(
            clearText(),
            closeSoftKeyboard()
        )
        onView(withId(R.id.signInEdtPassword)).perform(
            typeText("123456"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.loginButton)).perform(click())

        onView(withText(getString(R.string.invalid_email)))
            .inRoot(withDecorView(not(`is`(mActivityTestRule.activity.window.decorView))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun invalidPassword() {
        onView(withId(R.id.signInEdtEmail)).perform(
            typeText("test@gmail.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.signInEdtPassword)).perform(
            clearText(),
            closeSoftKeyboard()
        )
        onView(withId(R.id.loginButton)).perform(click())

        onView(withText(getString(R.string.invalid_password)))
            .inRoot(withDecorView(not(`is`(mActivityTestRule.activity.window.decorView))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun validEmailAndPassword() {
        onView(withId(R.id.signInEdtEmail)).perform(
            typeText("test@gmail.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.signInEdtPassword)).perform(
            typeText("123456"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.loginButton)).perform(click())

        onView(withText(getString(R.string.signin_success)))
            .inRoot(withDecorView(not(`is`(mActivityTestRule.activity.window.decorView))))
            .check(matches(isDisplayed()))

        assertTrue(mActivityTestRule.activity.isFinishing)
    }

    private fun getString(@StringRes resourceId: Int): String? {
        return mActivityTestRule.activity.getString(resourceId)
    }
}
