package org.dagger.mvp.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.AnimRes
import androidx.annotation.DimenRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import org.dagger.mvp.R
import org.dagger.mvp.ui.base.BaseFragment
import java.util.*

val matchParent: Int = android.view.ViewGroup.LayoutParams.MATCH_PARENT
val wrapContent: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT

//returns dip(dp) dimension value in pixels
fun Context.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()

fun Context.dip(value: Float): Int = (value * resources.displayMetrics.density).toInt()

//return sp dimension value in pixels
fun Context.sp(value: Int): Int = (value * resources.displayMetrics.scaledDensity).toInt()

fun Context.sp(value: Float): Int = (value * resources.displayMetrics.scaledDensity).toInt()

//converts px value into dip or sp
fun Context.px2dip(px: Int): Float = px.toFloat() / resources.displayMetrics.density

fun Context.px2sp(px: Int): Float = px.toFloat() / resources.displayMetrics.scaledDensity

fun Context.dimen(@DimenRes resource: Int): Int = resources.getDimensionPixelSize(resource)

//the same for the views
inline fun View.dip(value: Int): Int = context.dip(value)

inline fun View.dip(value: Float): Int = context.dip(value)
inline fun View.sp(value: Int): Int = context.sp(value)
inline fun View.sp(value: Float): Int = context.sp(value)
inline fun View.px2dip(px: Int): Float = context.px2dip(px)
inline fun View.px2sp(px: Int): Float = context.px2sp(px)
inline fun View.dimen(@DimenRes resource: Int): Int = context.dimen(resource)

fun IntRange.random() =
    Random().nextInt((endInclusive + 1) - start) + start

fun log(s: String?) {
    Log.d("Dagger MVP", s ?: "")
}

fun Context.isOnline(): Boolean {
    val netInfo =
        (applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}

fun ImageView.setIconColor(context: Context, color: Int) {
    this.setColorFilter(
        ContextCompat.getColor(context, color),
        android.graphics.PorterDuff.Mode.SRC_IN
    )
}

fun View.setInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun getFileSize(size: Long): String {
    return "${size.toDouble() / (1024 * 1024).toDouble()}".substring(0, 3) + " mb"
}

fun showMessage(context: Context?, message: String?) {
    Toast.makeText(context, message ?: "", Toast.LENGTH_SHORT).show()
}

fun View?.blockClickable(blockTimeMilles: Long = 500) {
    this?.isClickable = false
    Handler().postDelayed({ this?.isClickable = true }, blockTimeMilles)
}

fun Activity?.hideSoftInputFromWindow() {
    this?.let {
        if (it.currentFocus != null)
            (it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                it.currentFocus!!.windowToken,
                0
            )
    }
}

fun Activity?.showSoftInputFromWindow(view: View) {
    this?.let {
        if (it.currentFocus != null)
            (it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(
                view,
                InputMethodManager.SHOW_IMPLICIT
            )
    }
}

fun AppCompatActivity.openFragment(
    fragment: BaseFragment,
    isAnimate: Boolean = true, @AnimRes enterAnimRes: Int = R.anim.slide_from_right, @AnimRes exitAnimRes: Int = R.anim.slide_out_right
) {
    this.hideSoftInputFromWindow()
    val transaction = supportFragmentManager.beginTransaction()
    if (isAnimate) transaction.setCustomAnimations(
        enterAnimRes,
        exitAnimRes,
        enterAnimRes,
        exitAnimRes
    )
    transaction.addToBackStack(fragment.toString()).add(R.id.main_back, fragment)
        .commitAllowingStateLoss()
}
