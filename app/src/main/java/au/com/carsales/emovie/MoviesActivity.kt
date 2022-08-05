package au.com.carsales.emovie

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.viewbinding.ViewBinding
import au.com.carsales.emovie.databinding.ActivityMainBinding
import au.com.carsales.emovie.utils.base.BaseNavActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : BaseNavActivity() {

    companion object {
        const val splashFadeDurationMillis = 300
    }

    override fun layoutViewBinding(): ViewBinding = ActivityMainBinding.inflate(layoutInflater)
    override fun navHostFragmentId(): Int = R.id.nav_host_fragment
    override fun bottomNavigationView(): BottomNavigationView = (binding as ActivityMainBinding).bottomNavView

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashWasDisplayed = savedInstanceState != null

        if (!splashWasDisplayed) {
            val splashScreen = installSplashScreen()
            splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
                splashScreenViewProvider.iconView
                    .animate()
                    .setDuration(splashFadeDurationMillis.toLong())
                    .alpha(0f)
                    .withEndAction {
                        splashScreenViewProvider.remove()

                    }.start()
            }
        }

        setTheme(R.style.Theme_TestApp)
        super.onCreate(savedInstanceState)

    }
}