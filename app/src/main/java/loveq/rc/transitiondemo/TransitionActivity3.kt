package loveq.rc.transitiondemo

import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionInflater
import android.transition.Visibility
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class TransitionActivity3 : BaseDetailActivity() {
    private var type: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition3)
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
            TransitionInflater.from(this).inflateTransition(R.transition.slide_from_bottom)
        }
        window.enterTransition = transition
    }

    private fun buildEnterTransition(): Visibility {
        val slide = Slide()
        slide.duration = 500
        slide.slideEdge = Gravity.RIGHT
        return slide
    }

    private fun bindData() {
        val sample = intent.extras.getSerializable(BaseDetailActivity.EXTRA_SAMPLE) as Sample
        type = intent.extras.getInt(BaseDetailActivity.EXTRA_TYPE)
        val title = findViewById<TextView>(R.id.title)
        title.text = sample.name
        val squareRed = findViewById<ImageView>(R.id.square_red)
        squareRed.setColorFilter(sample.color)

    }
}
