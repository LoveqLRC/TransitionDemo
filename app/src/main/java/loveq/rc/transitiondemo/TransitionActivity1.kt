package loveq.rc.transitiondemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView

class TransitionActivity1 : BaseDetailActivity() {
    private var sample: Sample? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition1)
        bindData()
        setupLayout()
    }

    private fun setupLayout() {
        findViewById<View>(R.id.sample1_button1)
                .setOnClickListener({
                    var intent = Intent(this, TransitionActivity2::class.java)
                    intent.putExtra(EXTRA_SAMPLE, sample)
                    intent.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY)
                    tansitionTo(intent)
                })


    }

    private fun bindData() {
        sample = intent.extras.getSerializable(EXTRA_SAMPLE) as Sample
        var title = findViewById<TextView>(R.id.title)
        title.text = sample?.name
    }
}
