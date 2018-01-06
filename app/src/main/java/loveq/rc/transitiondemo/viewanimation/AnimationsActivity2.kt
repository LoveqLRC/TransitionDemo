package loveq.rc.transitiondemo.viewanimation

import android.os.Bundle
import android.transition.*
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_animations2.view.*
import loveq.rc.transitiondemo.BaseDetailActivity
import loveq.rc.transitiondemo.R
import java.util.*

class AnimationsActivity2 : BaseDetailActivity() {
    private val viewsToAnimate = ArrayList<View>()
    private val DELAY = 100
    private var scene0: Scene? = null
    private var scene1: Scene? = null
    private var scene2: Scene? = null
    private var scene3: Scene? = null
    private var scene4: Scene? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animations2)
        setupLayout()
        setupToolbar()
        setupWindowAnimations()
    }

    private fun setupWindowAnimations() {
        window.enterTransition = TransitionInflater.from(this).inflateTransition(R.transition.slide_from_bottom)

        window.enterTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {}

            override fun onTransitionCancel(transition: Transition) {}

            override fun onTransitionPause(transition: Transition) {}

            override fun onTransitionResume(transition: Transition) {}

            override fun onTransitionEnd(transition: Transition) {
                window.enterTransition.removeListener(this)
                TransitionManager.go(scene0)
            }
        })

    }

    private fun setupLayout() {
        var btnGroup = findViewById<ViewGroup>(R.id.buttons_group)
        val sceneRoot = findViewById<ViewGroup>(R.id.scene_root)

        scene0 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene0, this)
        scene0?.setEnterAction {
            for (child in viewsToAnimate) {
                child.animate().setStartDelay((viewsToAnimate.indexOf(child) * DELAY).toLong())
                        .scaleX(1f)
                        .scaleY(1f)
            }
        }

        scene0?.setExitAction {
            TransitionManager.beginDelayedTransition(btnGroup)
            val title = sceneRoot?.scene_root?.findViewById<View>(R.id.scene0_title)
            title?.scaleX = 0f
            title?.scaleY = 0f
        }

        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene1, this)
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene2, this)
        scene3 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene3, this)
        scene4 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene4, this)

        val button1 = findViewById<View>(R.id.sample3_button1)
        val button2 = findViewById<View>(R.id.sample3_button2)
        val button3 = findViewById<View>(R.id.sample3_button3)
        val button4 = findViewById<View>(R.id.sample3_button4)

        button1.setOnClickListener {
            TransitionManager.go(scene1,ChangeBounds())
        }
        viewsToAnimate.add(button1)
        viewsToAnimate.add(button2)
        viewsToAnimate.add(button3)
        viewsToAnimate.add(button4)


    }
}
