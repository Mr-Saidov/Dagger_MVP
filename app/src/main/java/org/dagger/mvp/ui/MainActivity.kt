package org.dagger.mvp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.dagger.mvp.R
import org.dagger.mvp.di.components.DaggerActivityComponent
import org.dagger.mvp.di.modules.ActivityModule
import org.dagger.mvp.ui.login.view.LoginFragment
import org.dagger.mvp.util.openFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerActivityComponent.builder().activityModule(ActivityModule(this)).build().inject(this)
        if (savedInstanceState == null) {
            openFragment(LoginFragment(), false)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else finish()
    }
}