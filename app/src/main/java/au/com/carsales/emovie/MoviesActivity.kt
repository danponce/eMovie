package au.com.carsales.emovie

import androidx.viewbinding.ViewBinding
import au.com.carsales.emovie.databinding.ActivityMainBinding
import au.com.carsales.emovie.utils.base.BaseNavActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : BaseNavActivity() {

    override fun layoutViewBinding(): ViewBinding = ActivityMainBinding.inflate(layoutInflater)
    override fun navHostFragmentId(): Int = R.id.nav_host_fragment
    override fun bottomNavigationView(): BottomNavigationView = (binding as ActivityMainBinding).bottomNavView

}