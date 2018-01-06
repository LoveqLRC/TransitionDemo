package loveq.rc.transitiondemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.transition.Slide
import android.view.Gravity
import loveq.rc.transitiondemo.entity.Sample

import java.util.*

class MainActivity : AppCompatActivity() {
    private var samples: List<Sample>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupWindowAnimations()
        setupSamples()
        setupToolbar()
        setupLayout()
    }

    private fun setupWindowAnimations() {
        var slide = Slide()
        slide.slideEdge = Gravity.LEFT
        slide.duration = 500
        //A->B->A  A页面进入B,B页面退出返回A是reenterTransition
        //A->B A页面进入B是exitTransition
        window.reenterTransition = slide
        window.exitTransition = slide
    }

    @SuppressLint("ResourceType")
    private fun setupSamples() {
        samples = Arrays.asList(
                Sample(ContextCompat.getColor(this, R.color.sample_red), "Transitions"),
                Sample(ContextCompat.getColor(this, R.color.sample_blue), "Shared Elements"),
                Sample(ContextCompat.getColor(this, R.color.sample_green), "View animations"),
                Sample(ContextCompat.getColor(this, R.color.sample_yellow), "Circular Reveal Animation")
        )
    }

    private fun setupToolbar() {
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }


    private fun setupLayout() {
        val recyclerView = findViewById<RecyclerView>(R.id.sample_list)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val recyclerAdapter = SamplesRecyclerAdapter(this, samples!!)
        recyclerView.adapter = recyclerAdapter
    }


}
