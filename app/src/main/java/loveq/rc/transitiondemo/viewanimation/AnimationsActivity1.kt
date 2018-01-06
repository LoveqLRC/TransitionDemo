package loveq.rc.transitiondemo.viewanimation

import android.content.Intent
import android.os.Bundle
import android.support.v4.graphics.drawable.DrawableCompat
import android.transition.Fade
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import loveq.rc.transitiondemo.BaseDetailActivity
import loveq.rc.transitiondemo.R
import loveq.rc.transitiondemo.entity.Sample

class AnimationsActivity1 : BaseDetailActivity() {
    private var root: ViewGroup? = null
    private var squareGreen: ImageView? = null
    private var sizeChanged: Boolean = false
    private var positionChanged: Boolean = false
    private var savedWidth: Int = 0
    private var sample: Sample? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animations1)
        bindData()
        setupWindowsAnimations()
        setupLayout()
    }

    private fun setupLayout() {
        squareGreen = findViewById(R.id.square_green)
        root = findViewById(R.id.sample3_root)
        findViewById<View>(R.id.sample3_button1).setOnClickListener {
            changeLayout()
        }
        findViewById<View>(R.id.sample3_button2).setOnClickListener {
            changePosition()
        }
        findViewById<View>(R.id.sample3_button3).setOnClickListener {
            val intent = Intent(this, AnimationsActivity2::class.java)
            intent.putExtra(EXTRA_SAMPLE, sample)
            transitionTo(intent)
        }

    }

    private fun changePosition() {
        TransitionManager.beginDelayedTransition(root)
        var params = squareGreen?.layoutParams as LinearLayout.LayoutParams
        if (positionChanged) {
            params.gravity = Gravity.CENTER
        } else {
            params.gravity = Gravity.LEFT
        }
        positionChanged = !positionChanged
        squareGreen?.layoutParams = params

    }

    private fun changeLayout() {
        TransitionManager.beginDelayedTransition(root)
        val params = squareGreen?.layoutParams
        if (sizeChanged) {
            params?.width = savedWidth
        } else {
            savedWidth = params!!.width
            params.width = 200
        }
        sizeChanged = !sizeChanged
        squareGreen?.layoutParams = params
    }

    private fun setupWindowsAnimations() {
        window.enterTransition = Fade()
    }

    private fun bindData() {
        sample = intent.extras.get(EXTRA_SAMPLE) as Sample
        val title = findViewById<TextView>(R.id.title)
        title.text = sample?.name
        var squareGreen = findViewById<ImageView>(R.id.square_green)
        DrawableCompat.setTint(squareGreen.drawable, sample!!.color)

    }
}
