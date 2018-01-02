package loveq.rc.transitiondemo

import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

/**
 * Created by rc on 2017/12/28.
 * Description:
 */
open class BaseDetailActivity : AppCompatActivity() {
    fun setupToolbar() {
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        //是否显示返回按钮
        actionBar.setDisplayHomeAsUpEnabled(true)
        //是否显示标题
        actionBar.setDisplayShowTitleEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun tansitionTo(intent: Intent) {
        var participants = createSafeTransitionParticipants(this, true)
        var optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, *participants)
        startActivity(intent, optionsCompat.toBundle())
    }


    companion object {
        val EXTRA_SAMPLE = "sample"
        val EXTRA_TYPE = "type"
        val TYPE_PROGRAMMATICALLY = 0
        val TYPE_XML = 1
    }
}