package loveq.rc.transitiondemo

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast


/**
 * Created by rc on 2017/12/28.
 * Description:
 */
class SamplesRecyclerAdapter(private val activity: Activity, private val samples: List<Sample>) : RecyclerView.Adapter<SamplesRecyclerAdapter.SampleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SampleViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.row_sample, parent, false)
        return SampleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return samples.size
    }

    override fun onBindViewHolder(holder: SampleViewHolder?, position: Int) {
        var sample = samples[holder!!.adapterPosition]
        holder.mSampleIcon.setColorFilter(sample.color)
        holder.mSampleName.text = sample.name
        holder.itemView.setOnClickListener {
            when (holder.adapterPosition) {
                0 -> {
                    transitionToActivity(TransitionActivity1::class.java, sample)
                }
                else -> {
                    Toast.makeText(activity, "hello world  213", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    class SampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mSampleIcon: ImageView = itemView.findViewById(R.id.sample_icon)
        var mSampleName: TextView = itemView.findViewById(R.id.sample_name)
    }

    private fun transitionToActivity(target: Class<*>, sample: Sample) {
        var participants = createSafeTransitionParticipants(activity, true)
        startActivity(target,participants,sample)
    }

    private fun startActivity(target: Class<*>, pairs: Array<Pair<View, String>>, sample: Sample) {
        val i = Intent(activity, target)
        val transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, *pairs)
        i.putExtra("sample", sample)
        activity.startActivity(i, transitionActivityOptions.toBundle())
    }

}