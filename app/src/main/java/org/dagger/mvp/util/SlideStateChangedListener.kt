package org.dagger.mvp.util

import com.r0adkll.slidr.model.SlidrListener

class SlideStateChangedListener(val slideStateChangedCallback: SlideStateChangedCallback) :
    SlidrListener {
    override fun onSlideClosed() {
    }

    override fun onSlideStateChanged(state: Int) {
        slideStateChangedCallback.onSlideStateChanged(state)
    }

    override fun onSlideChange(percent: Float) {
    }

    override fun onSlideOpened() {
    }

    interface SlideStateChangedCallback {
        fun onSlideStateChanged(state: Int)
    }
}