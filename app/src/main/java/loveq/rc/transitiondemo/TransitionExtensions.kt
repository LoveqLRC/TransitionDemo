@file:JvmName("TransitionHelper")

package loveq.rc.transitiondemo

import android.app.Activity
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.view.View
import java.util.*
import android.support.v4.util.Pair

/**
 * Created by rc on 2017/12/28.
 * Description:
 */
fun createSafeTransitionParticipants(@NonNull activity: Activity, includeStatusBar: Boolean,
                                     @Nullable vararg otherParticipants: Pair<View, String>?): Array<Pair<View, String>> {
    var decorView = activity.window.decorView
    var statusBar: View? = null
    if (includeStatusBar) {
        statusBar = decorView.findViewById<View>(android.R.id.statusBarBackground)
    }
    var navBar = decorView.findViewById<View>(android.R.id.navigationBarBackground)

    val participants = ArrayList<Pair<View, String>>(3)

    addNonNullViewToTransitionParticipants(statusBar, participants)
    addNonNullViewToTransitionParticipants(navBar, participants)

    if (!(otherParticipants.size == 1 && otherParticipants[0] == null)) {
        participants.addAll(Arrays.asList<Pair<View, String>>(*otherParticipants))
    }
    return participants.toTypedArray()
}

fun addNonNullViewToTransitionParticipants(view: View?, participants: MutableList<Pair<View, String>>) {
    if (view == null) {
        return
    }
    participants.add(Pair(view, view.transitionName))
}