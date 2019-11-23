package org.dagger.mvp.util

import android.content.Context
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import org.dagger.mvp.R

object MyAnimationUtil {

    val TYPE_FALL_DOWN = 0
    val TYPE_FROM_BOTTOM = 1
    val TYPE_SLIDE_RIGHT = 2

    fun getLayoutAnimation(context: Context, type: Int): LayoutAnimationController {
        if (type == TYPE_SLIDE_RIGHT)
            return AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_right)
        if (type == TYPE_FROM_BOTTOM)
            return AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_bottom)
        else
            return AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
    }
}