package loveq.rc.transitiondemo.sharedelement

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Slide
import android.view.Gravity
import android.widget.TextView
import loveq.rc.transitiondemo.BaseDetailActivity
import loveq.rc.transitiondemo.R
import loveq.rc.transitiondemo.entity.Sample

class SharedElementActivity : BaseDetailActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_element)
        var sample = intent.extras.getSerializable(EXTRA_SAMPLE) as Sample
        bindData(sample)
        setupWindowAnimations()
        setupLayout(sample)
        setupToolbar()
    }

    private fun setupLayout(sample: Sample) {
        //为Fragment创建Transition
        val slideTransition = Slide(Gravity.START)
        slideTransition.duration = 500
        val shareElementFragment1 = SharedElementFragment1.newInstance(sample)
        shareElementFragment1.reenterTransition = slideTransition
        shareElementFragment1.exitTransition = slideTransition
        shareElementFragment1.sharedElementEnterTransition = ChangeBounds()

        supportFragmentManager.beginTransaction()
                .replace(R.id.sample2_content, shareElementFragment1)
                .commit()
    }

    private fun setupWindowAnimations() {
        window.enterTransition.duration = 500
    }

    private fun bindData(sample: Sample) {
        var title = findViewById<TextView>(R.id.title)
        title.text = sample.name
    }

}
