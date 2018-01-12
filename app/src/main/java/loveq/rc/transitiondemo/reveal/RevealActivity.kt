package loveq.rc.transitiondemo.reveal

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.Toolbar
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import loveq.rc.transitiondemo.BaseDetailActivity
import loveq.rc.transitiondemo.R
import loveq.rc.transitiondemo.entity.Sample

class RevealActivity : BaseDetailActivity() {
    private val DELAY = 100
    private var loadInterpolator: Interpolator? = null
    private var root: RelativeLayout? = null
    private var body: TextView? = null
    private var toolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reveal)
        bindData()
        setupWindowAnimations()
        setupLayout()
        setupToolbar()
    }

    private fun setupLayout() {
        root = findViewById<RelativeLayout>(R.id.reveal_root)
        body = findViewById<TextView>(R.id.sample_body)
        findViewById<View>(R.id.square_green).setOnClickListener {
            revealGreen()
        }
    }

    private fun revealGreen() {
        animateRevealColor(root!!, R.color.sample_green)
    }

    private fun animateRevealColor(root: View, @ColorRes color: Int) {
        var cx = (root.left + root.right) / 2

    }

    private fun setupWindowAnimations() {
        loadInterpolator = AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in)
        setupEnterAnimations()
        setupExitAnimations()
    }

    private fun setupExitAnimations() {
        val fade = Fade()
        window.returnTransition = fade
        fade.duration = 500
        fade.startDelay = 300
        fade.addListener(object : Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition?) {
            }

            override fun onTransitionResume(transition: Transition?) {
            }

            override fun onTransitionPause(transition: Transition?) {
            }

            override fun onTransitionCancel(transition: Transition?) {
            }

            override fun onTransitionStart(transition: Transition?) {
                transition?.removeListener(this)
                animateButtonsOut()
                animateRevealHide(root!!)
            }
        })

    }

    private fun animateRevealHide(root: View) {
        val cx = (root.left + root.right) / 2
        val cy = (root.top + root.bottom) / 2
        val width = root.width
        val anim = ViewAnimationUtils.createCircularReveal(root, cx, cy, width.toFloat(), 0F)
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                root.visibility = View.INVISIBLE
            }
        })
        anim.duration = 500
        anim.start()
    }

    private fun animateButtonsOut() {
        for (i in 0 until root!!.childCount) {
            val child = root!!.getChildAt(i)
            child.animate()
                    .setStartDelay(i.toLong())
                    .setInterpolator(loadInterpolator)
                    .alpha(0f)
                    .scaleX(0f)
                    .scaleY(0f)
        }
    }

    private fun setupEnterAnimations() {
        val transition = TransitionInflater.from(this).inflateTransition(R.transition.changebounds_with_arcmotion)
        window.sharedElementEnterTransition = transition
        transition.addListener(object : Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition?) {
                transition?.removeListener(this)
                hideTarget()
                animateRevealShow(toolbar!!)
                animateButtonsIn()
            }

            override fun onTransitionResume(transition: Transition?) {
            }

            override fun onTransitionPause(transition: Transition?) {
            }

            override fun onTransitionCancel(transition: Transition?) {
            }

            override fun onTransitionStart(transition: Transition?) {
            }
        })
    }

    private fun animateButtonsIn() {
        (0 until root!!.childCount)
                .asSequence()
                .map { root?.getChildAt(it) }
                .forEach {
                    it!!.animate()
                            .setStartDelay((100 + 1 * DELAY).toLong())
                            .setInterpolator(loadInterpolator)
                            .alpha(1f)
                            .scaleX(1f)
                            .scaleY(1f)
                            .start()
                }
    }

    private fun animateRevealShow(root: View) {
        val cx = (root.left + root.right) / 2
        val cy = (root.top + root.bottom) / 2
        val radius = Math.max(root.width, root.height)
        val anim = ViewAnimationUtils.createCircularReveal(root, cx, cy, 0f, radius.toFloat())
        root.visibility = View.VISIBLE
        anim.duration = 500
        anim.interpolator = AccelerateInterpolator()
        anim.start()
    }

    private fun hideTarget() {
        findViewById<View>(R.id.shared_target).visibility = View.GONE
    }

    private fun bindData() {
        val sample = intent.extras.getSerializable(EXTRA_SAMPLE) as Sample
        val title = findViewById<TextView>(R.id.title)
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        title.text = sample.name
        val sharedTarget = findViewById<ImageView>(R.id.shared_target)
        DrawableCompat.setTint(sharedTarget.drawable, sample.color)
    }
}
