package com.hi.mvvmkotlin.ui.signup

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
open class SignUpActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SignUpActivity::class.java)

    @Test
    fun invalidUserName() {
        onView(withId(R.id.signUpEdtUsername)).perform(
            clearText(),
            closeSoftKeyboard()
        )
        onView(withId(R.id.signUpEdtEmail)).perform(
            typeText("test@gmail.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.signUpEdtPassword)).perform(
            typeText("123456"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.registerButton)).perform(click())

        onView(withText(getString(R.string.invalid_username)))
            .inRoot(withDecorView(not(`is`(mActivityTestRule.activity.window.decorView))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun invalidEmail() {
        onView(withId(R.id.signUpEdtUsername)).perform(
            typeText("test"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.signUpEdtEmail)).perform(
            clearText(),
            closeSoftKeyboard()
        )
        onView(withId(R.id.signUpEdtPassword)).perform(
            typeText("123456"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.registerButton)).perform(click())

        onView(withText(getString(R.string.invalid_email)))
            .inRoot(withDecorView(not(`is`(mActivityTestRule.activity.window.decorView))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun invalidPassword() {
        onView(withId(R.id.signUpEdtUsername)).perform(
            typeText("test"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.signUpEdtEmail)).perform(
            typeText("test@gmail.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.signUpEdtPassword)).perform(
            clearText(),
            closeSoftKeyboard()
        )
        onView(withId(R.id.registerButton)).perform(click())

        onView(withText(getString(R.string.invalid_password)))
            .inRoot(withDecorView(not(`is`(mActivityTestRule.activity.window.decorView))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun validEmailAndPassword() {
        onView(withId(R.id.signUpEdtUsername)).perform(
            typeText("test"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.signUpEdtEmail)).perform(
            typeText("test@gmail.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.signUpEdtPassword)).perform(
            typeText("123456"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.registerButton)).perform(click())

        onView(withText(getString(R.string.signup_success)))
            .inRoot(withDecorView(not(`is`(mActivityTestRule.activity.window.decorView))))
            .check(matches(isDisplayed()))

        assertTrue(mActivityTestRule.activity.isFinishing)
    }

    @Test
    fun userAlreadyAvailable() {
        onView(withId(R.id.signUpEdtUsername)).perform(
            typeText("test"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.signUpEdtEmail)).perform(
            typeText("test@gmail.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.signUpEdtPassword)).perform(
            typeText("123456"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.registerButton)).perform(click())

        onView(withText(getString(R.string.user_already_available)))
            .inRoot(withDecorView(not(`is`(mActivityTestRule.activity.window.decorView))))
            .check(matches(isDisplayed()))
    }

    private fun getString(@StringRes resourceId: Int): String? {
        return mActivityTestRule.activity.getString(resourceId)
    }
}
