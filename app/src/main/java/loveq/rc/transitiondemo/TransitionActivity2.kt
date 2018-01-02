package loveq.rc.transitiondemo

import android.os.Bundle
import android.transition.Explode
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class TransitionActivity2 : BaseDetailActivity() {
    private var type: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition2)
        bindData()
        setupWindowAnimations()
        setupLayout()
        setupToolbar()
    }

    private fun setupLayout() {
        findViewById<View>(R.id.exit_button)
                .setOnClickListener {
                    finishAfterTransition()
                }
    }

    private fun setupWindowAnimations() {
        val transition = if (type == TYPE_PROGRAMMATICALLY) {
            buildEnterTransition()
        } else {
            TransitionInflater.from(this).inflateTransition(R.transition.explode)
        }
        window.enterTransition = transition
    }

    private fun buildEnterTransition(): Transition {
        val enterTransition = Explode()
        enterTransition.duration = 500
        return enterTransition
    }

    private fun bindData() {
        var sample = intent.extras.getSerializable(EXTRA_SAMPLE) as Sample
        type = intent.extras.getInt(EXTRA_TYPE)
        var title = findViewById<TextView>(R.id.title)
        title.text = sample.name
        var squareRed = findViewById<ImageView>(R.id.square_red)
        squareRed.setColorFilter(sample.color)

    }
}
