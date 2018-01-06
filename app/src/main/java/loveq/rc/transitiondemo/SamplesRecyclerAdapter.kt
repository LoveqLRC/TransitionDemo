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
import loveq.rc.transitiondemo.entity.Sample
import loveq.rc.transitiondemo.sharedelement.SharedElementActivity
import loveq.rc.transitiondemo.transition.TransitionActivity1
import loveq.rc.transitiondemo.viewanimation.AnimationsActivity1



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
                1 -> {
                    transitionToActivity(SharedElementActivity::class.java, holder, sample)
                }
                2 -> {
                    transitionToActivity(AnimationsActivity1::class.java, sample)
                }
                3 -> {

                }
            }

        }
    }

    class SampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mSampleIcon: ImageView = itemView.findViewById(R.id.sample_icon)
        var mSampleName: TextView = itemView.findViewById(R.id.sample_name)
    }

    private fun transitionToActivity(target: Class<*>, sample: Sample) {
        val participants = createSafeTransitionParticipants(activity, true)
        startActivity(target, participants, sample)
    }

    private fun transitionToActivity(target: Class<*>, holder: SampleViewHolder, sample: Sample) {
        val pairs = createSafeTransitionParticipants(activity, false,
                Pair(holder.mSampleIcon, activity.getString(R.string.square_blue_name)),
                Pair(holder.mSampleName, activity.getString(R.string.sample_blue_title)))
        startActivity(target, pairs, sample)
    }

    private fun startActivity(target: Class<*>, pairs: Array<Pair<View, String>>, sample: Sample) {
        val i = Intent(activity, target)
        val transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, *pairs)
        i.putExtra("sample", sample)
        activity.startActivity(i, transitionActivityOptions.toBundle())
    }

}