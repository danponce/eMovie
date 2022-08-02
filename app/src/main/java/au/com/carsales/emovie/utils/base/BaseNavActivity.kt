package au.com.carsales.emovie.utils.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.ViewBinding
import au.com.carsales.emovie.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Created by Dan on 24, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
abstract class BaseNavActivity : AppCompatActivity(), NavigationHelperListener {

    abstract fun layoutViewBinding() : ViewBinding
    abstract fun navHostFragmentId() : Int
    abstract fun bottomNavigationView() : BottomNavigationView

    private lateinit var navController : NavController
    protected lateinit var binding : ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = layoutViewBinding()
        setContentView(binding.root)
        setNavController()
    }

    private fun setNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(navHostFragmentId()) as NavHostFragment
        navController = navHostFragment.navController

        bottomNavigationView().setupWithNavController(navController)
    }

    override fun navigate(directions: NavDirections, extras : FragmentNavigator.Extras?) {
        when {
            extras != null -> {
                navController.navigate(directions, extras)
            }
            else -> {
                navController.navigate(directions)
            }
        }

    }

    override fun navigateBack() = navController.popBackStack()
}