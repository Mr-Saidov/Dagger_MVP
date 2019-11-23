package org.dagger.mvp.ui.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.AnimRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.r0adkll.slidr.Slidr
import com.r0adkll.slidr.model.SlidrConfig
import com.r0adkll.slidr.model.SlidrInterface
import com.r0adkll.slidr.model.SlidrPosition
import org.dagger.mvp.R
import org.dagger.mvp.util.SlideStateChangedListener
import org.dagger.mvp.util.hideSoftInputFromWindow

abstract class BaseFragment(
    private @LayoutRes val layoutId: Int, private val canSwipe: Boolean = true,
    private val swipePosition: SlidrPosition = SlidrPosition.LEFT
) : Fragment() {

    private lateinit var oldSreenView: FrameLayout
    private var slidrInterface: SlidrInterface? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        oldSreenView = FrameLayout(context!!).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(Color.WHITE)
            addView(inflater.inflate(layoutId, container, false))
        }
        return FrameLayout(context!!).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(Color.TRANSPARENT)

            // joriy fragmentni swipe i o'chirilganda orqadagi fragmentga click eventi o'tmasligi uchun
            isClickable = true
            isEnabled = false

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) isTransitionGroup = true
            addView(oldSreenView)
        }
    }

    fun openFragment(
        fragment: BaseFragment,
        isAnimate: Boolean = true, @AnimRes enterAnimRes: Int = R.anim.slide_from_right, @AnimRes exitAnimRes: Int = R.anim.slide_out_right
    ) {
        activity.hideSoftInputFromWindow()
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        if (isAnimate) transaction.setCustomAnimations(
            enterAnimRes,
            exitAnimRes,
            enterAnimRes,
            exitAnimRes
        )
        transaction.addToBackStack(fragment.toString()).add(R.id.main_back, fragment)
            .commitAllowingStateLoss()
    }

    fun closeActiveFragment() {
        if (activity!!.supportFragmentManager.backStackEntryCount > 1)
            activity!!.supportFragmentManager.popBackStack()
    }

    fun closeAllFragmentsAndOpenThis(fragment: BaseFragment, isAnimate: Boolean = true) {
        activity!!.supportFragmentManager.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        openFragment(fragment, isAnimate)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependency()
        onViewCreate()
    }

    override fun onResume() {
        super.onResume()
        if (canSwipe && slidrInterface == null) {
            slidrInterface =
                Slidr.replace(
                    oldSreenView,
                    SlidrConfig.Builder()
                        .position(swipePosition)
                        .listener(
                            SlideStateChangedListener(object :
                                SlideStateChangedListener.SlideStateChangedCallback {
                                override fun onSlideStateChanged(state: Int) {
                                    activity.hideSoftInputFromWindow()
                                }
                            })
                        )
                        .build()
                )
        }
    }

    fun lockSwipable() {
        slidrInterface?.lock()
    }

    fun unlockSwipable() {
        slidrInterface?.unlock()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unSubscribePresenter()
    }

    abstract fun onViewCreate()
    abstract fun injectDependency()
    abstract fun unSubscribePresenter()
}