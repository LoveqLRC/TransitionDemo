package loveq.rc.transitiondemo

import android.support.annotation.ColorRes
import android.support.v4.graphics.drawable.DrawableCompat
import android.widget.ImageView
import java.io.Serializable

/**
 * Created by rc on 2017/12/28.
 * Description:
 */
class Sample(@param:ColorRes val color: Int, val name: String) : Serializable {
    companion object {
        fun setColorTint(view: ImageView, @ColorRes color: Int) {
            DrawableCompat.setTint(view.drawable, color)
        }
    }
}