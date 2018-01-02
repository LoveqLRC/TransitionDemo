package loveq.rc.transitiondemo

import android.content.Intent
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.transition.Visibility
import android.view.View
import android.widget.TextView

class TransitionActivity1 : BaseDetailActivity() {
    private var sample: Sample? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition1)
        bindData()
        setupWindowAnimations()
        setupLayout()
        setupToolbar()
    }

    private fun setupWindowAnimations() {
        val enterTransition = buildEnterTransition()
        window.enterTransition = enterTransition
    }

    private fun buildEnterTransition(): Visibility {
        val enterTransition = Fade()
        enterTransition.duration = 500
        // enterTransition动画排除R.id.square_red
        enterTransition.excludeTarget(R.id.square_red, true)
        return enterTransition
    }

    private fun setupLayout() {
        findViewById<View>(R.id.sample1_button1)
                .setOnClickListener({
                    val intent = Intent(this, TransitionActivity2::class.java)
                    intent.putExtra(EXTRA_SAMPLE, sample)
                    intent.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY)
                    transitionTo(intent)
                })
        findViewById<View>(R.id.sample1_button2)
                .setOnClickListener {
                    val intent = Intent(this, TransitionActivity2::class.java)
                    intent.putExtra(EXTRA_SAMPLE, sample)
                    intent.putExtra(EXTRA_TYPE, TYPE_XML)
                    transitionTo(intent)
                }
        findViewById<View>(R.id.sample1_button3)
                .setOnClickListener {
                    val intent = Intent(this, TransitionActivity3::class.java)
                    intent.putExtra(EXTRA_SAMPLE, sample)
                    intent.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY)
                    transitionTo(intent)
                }

        findViewById<View>(R.id.sample1_button4)
                .setOnClickListener {
                    val intent = Intent(this, TransitionActivity3::class.java)
                    intent.putExtra(EXTRA_SAMPLE, sample)
                    intent.putExtra(EXTRA_TYPE, TYPE_XML)
                    transitionTo(intent)
                }
        findViewById<View>(R.id.sample1_button5)
                .setOnClickListener {
                    val returnTransition = buildReturnTransition()
                    window.returnTransition = returnTransition
                    finishAfterTransition()
                }

        findViewById<View>(R.id.sample1_button6)
                .setOnClickListener {
                    finishAfterTransition()
                }
    }

    private fun buildReturnTransition(): Visibility {
        val returnTransition = Slide()
        returnTransition.duration = 500
        return returnTransition
    }

    private fun bindData() {
        sample = intent.extras.getSerializable(EXTRA_SAMPLE) as Sample
        var title = findViewById<TextView>(R.id.title)
        title.text = sample?.name
    }
}
