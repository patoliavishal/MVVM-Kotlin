package com.hi.mvvmkotlin.ui.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.hi.mvvmkotlin.BR
import com.hi.mvvmkotlin.R
import com.hi.mvvmkotlin.ViewModelProviderFactory
import com.hi.mvvmkotlin.databinding.ActivitySigninBinding
import com.hi.mvvmkotlin.ui.base.BaseActivity
import com.hi.mvvmkotlin.ui.fakedata.FakeDataListActivity
import com.hi.mvvmkotlin.ui.signup.SignUpActivity
import com.hi.mvvmkotlin.utils.setBackgroundImage
import javax.inject.Inject

/**
 * Created by Vishal Patel on 11/10/19.
 */
class SignInActivity : BaseActivity<ActivitySigninBinding, SignInViewModel>(),
    SignInNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private var mActivitySignInBinding: ActivitySigninBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_signin
    override val viewModel: SignInViewModel
        get() = ViewModelProviders.of(this, factory).get(SignInViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivitySignInBinding = getViewDataBinding()
        viewModel.navigator = this

        setBackgroundImage(mActivitySignInBinding!!.bgImageView, R.drawable.login_background)
    }

    override fun signIn() {
        viewModel.isCheckEmailPassword(
            mActivitySignInBinding?.signInEdtEmail?.text.toString().trim(),
            mActivitySignInBinding?.signInEdtPassword?.text.toString().trim()
        )
    }

    override fun signUp() {
        val intentSignUp = Intent(this, SignUpActivity::class.java)
        intentSignUp.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intentSignUp)
        finish()
    }

    override fun onFacebookSignIn() {
        Toast.makeText(this, getString(R.string.fb_signin), Toast.LENGTH_SHORT).show()
    }

    override fun onTwitterSignIn() {
        Toast.makeText(this, getString(R.string.twitter_signin), Toast.LENGTH_SHORT).show()
    }

    override fun onGoogleSignIn() {
        Toast.makeText(this, getString(R.string.google_signin), Toast.LENGTH_SHORT).show()
    }

    override fun signInSuccess(message: String) {
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