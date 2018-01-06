package loveq.rc.transitiondemo.sharedelement

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.graphics.drawable.DrawableCompat
import android.transition.ChangeBounds
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import loveq.rc.transitiondemo.R
import loveq.rc.transitiondemo.entity.Sample


/**
 * A simple [Fragment] subclass.
 */
class SharedElementFragment1 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_sharedelement_fragment1, container, false)
        val sample = arguments?.getSerializable(EXTRA_SAMPLE) as Sample
        val squareBlue = view?.findViewById<ImageView>(R.id.square_blue)
        DrawableCompat.setTint(squareBlue!!.drawable, sample.color)
        view.findViewById<View>(R.id.sample2_button1)
                .setOnClickListener {
                    addNextFragment(sample, squareBlue, false)
                }
        view.findViewById<View>(R.id.sample2_button2)
                .setOnClickListener {
                    addNextFragment(sample, squareBlue, true)
                }

        return view
    }

    private fun addNextFragment(sample: Sample, square: ImageView, overlap: Boolean) {
        val shareElementFragment2 = SharedElementFragment2.newInstance(sample)
        val slideTransition = Slide(Gravity.RIGHT)
        slideTransition.duration = 300

        val changeBoundsTransition = ChangeBounds()
        changeBoundsTransition.duration = 300

        shareElementFragment2.enterTransition = slideTransition
        shareElementFragment2.allowEnterTransitionOverlap = overlap
        shareElementFragment2.allowReturnTransitionOverlap = overlap
        shareElementFragment2.sharedElementEnterTransition = changeBoundsTransition

        fragmentManager!!.beginTransaction()
                .replace(R.id.sample2_content, shareElementFragment2)
                .addSharedElement(square, getString(R.string.square_blue_name))
                .commit()
    }

    companion object {
        private val EXTRA_SAMPLE = "sample"

        fun newInstance(sample: Sample): SharedElementFragment1 {
            val args = Bundle()
            args.putSerializable(EXTRA_SAMPLE, sample)
            val fragment = SharedElementFragment1()
            fragment.arguments = args
            return fragment
        }
    }
}