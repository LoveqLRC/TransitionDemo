package loveq.rc.transitiondemo.sharedelement


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.graphics.drawable.DrawableCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import loveq.rc.transitiondemo.R
import loveq.rc.transitiondemo.entity.Sample


/**
 * A simple [Fragment] subclass.
 */
class SharedElementFragment2 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_shared_element_fragment2, container, false)
        var sample = arguments?.getSerializable(EXTRA_SAMPLE) as Sample
        var squareBlue = view.findViewById<ImageView>(R.id.square_blue)
        DrawableCompat.setTint(squareBlue.drawable,sample.color)

        return view
    }


    companion object {
        private val EXTRA_SAMPLE = "sample"

        fun newInstance(sample: Sample): SharedElementFragment2 {
            val args = Bundle()
            args.putSerializable(EXTRA_SAMPLE, sample)
            val fragment = SharedElementFragment2()
            fragment.arguments = args
            return fragment
        }
    }

}
