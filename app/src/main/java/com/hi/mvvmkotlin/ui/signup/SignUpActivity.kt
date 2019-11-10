package com.hi.mvvmkotlin.ui.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.hi.mvvmkotlin.BR
import com.hi.mvvmkotlin.R
import com.hi.mvvmkotlin.ViewModelProviderFactory
import com.hi.mvvmkotlin.databinding.ActivitySignupBinding
import com.hi.mvvmkotlin.ui.base.BaseActivity
import com.hi.mvvmkotlin.ui.fakedata.FakeDataListActivity
import com.hi.mvvmkotlin.ui.signin.SignInActivity
import com.hi.mvvmkotlin.utils.setBackgroundImage
import javax.inject.Inject

/**
 * Created by Vishal Patel on 11/10/19.
 */
class SignUpActivity : BaseActivity<ActivitySignupBinding, SignUpViewModel>(),
    SignUpNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private var mActivitySignUpBinding: ActivitySignupBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_signup
    override val viewModel: SignUpViewModel
        get() = ViewModelProviders.of(this, factory).get(SignUpViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivitySignUpBinding = getViewDataBinding()
        viewModel.navigator = this

        setBackgroundImage(mActivitySignUpBinding!!.bgImageView, R.drawable.login_background)
    }

    override fun signUp() {
        viewModel.isCheckDetails(
            mActivitySignUpBinding?.signUpEdtEmail?.text.toString().trim(),
            mActivitySignUpBinding?.signUpEdtPassword?.text.toString().trim(),
            mActivitySignUpBinding?.signUpEdtUsername?.text.toString().trim()
        )
    }

    override fun signIn() {
        val intentSignIn = Intent(this, SignInActivity::class.java)
        intentSignIn.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intentSignIn)
        finish()
    }

    override fun signUpSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        val intentSignIn = Intent(this, FakeDataListActivity::class.java)
        intentSignIn.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intentSignIn)
        finish()
    }

    override fun noInternetConnection() {
        viewModel.setIsLoading(false)
    }

    override fun handleError(throwable: Throwable) {
        viewModel.setIsLoading(false)
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        finish()
    }
}