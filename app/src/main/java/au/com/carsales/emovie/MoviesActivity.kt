package au.com.carsales.emovie

import au.com.carsales.emovie.utils.base.BaseNavActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : BaseNavActivity() {
    override fun layoutId(): Int = R.layout.activity_main
    override fun navHostFragmentId(): Int = R.id.nav_host_fragment

}