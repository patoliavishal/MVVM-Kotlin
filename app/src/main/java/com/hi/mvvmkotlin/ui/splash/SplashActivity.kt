package com.hi.mvvmkotlin.ui.splash

import android.animation.Animator
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hi.mvvmkotlin.R
import com.hi.mvvmkotlin.ui.signin.SignInActivity
import com.hi.mvvmkotlin.utils.Utils
import com.hi.mvvmkotlin.utils.setBackgroundImage
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Created by Vishal Patel on 11/10/19.
 */
class SplashActivity : AppCompatActivity() {

    var isRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setBackgroundImage(s1bgImageVIew, R.drawable.login_background)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        //Here you can get the size!

        if (!isRunning) {
            isRunning = true
            val width = Resources.getSystem().displayMetrics.widthPixels
            val height = Resources.getSystem().displayMetrics.heightPixels

            val halfWidth = width / 2
            val halfHeight = height / 2

            val px50 = Utils.dpToPx(this, 50)
            val px20 = Utils.dpToPx(this, 20)
            val px90 = Utils.dpToPx(this, 90)

            val iconToX = halfWidth - px50
            val iconToY = halfHeight - px90

            iconImageView.animate().alpha(1f).translationX(iconToX.toFloat())
                .translationY(iconToY.toFloat()).setDuration(1500)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationEnd(p0: Animator?) {
                        val intent = Intent(this@SplashActivity, SignInActivity::class.java)
                        intent.flags =
                            (Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    }

                    override fun onAnimationStart(animator: Animator?) {
                    }

                    override fun onAnimationCancel(animator: Animator?) {
                    }

                    override fun onAnimationRepeat(animator: Animator?) {
                    }
                }).start()

            val textToX = halfWidth - nameTextView.width / 2
            val textToY = halfHeight + px20

            nameTextView.animate().alpha(0f).translationY(height.toFloat())
                .translationX(width.toFloat()).setDuration(0)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animator: Animator?) {
                    }

                    override fun onAnimationEnd(animator: Animator?) {
                        nameTextView.animate().alpha(1f).translationX(textToX.toFloat())
                            .translationY(textToY.toFloat()).setDuration(1500).start()
                    }

                    override fun onAnimationCancel(animator: Animator?) {
                    }

                    override fun onAnimationRepeat(animator: Animator?) {
                    }
                }).start()
        }
    }
}
