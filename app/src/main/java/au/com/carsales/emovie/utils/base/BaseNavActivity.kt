package au.com.carsales.emovie.utils.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment

/**
 * Created by Dan on 24, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
abstract class BaseNavActivity : AppCompatActivity(), NavigationHelperListener {

    abstract fun layoutId() : Int
    abstract fun navHostFragmentId() : Int

    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutId())
        setNavController()
    }

    private fun replaceFragment(fragment : Fragment, containerId: Int) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(containerId, fragment)
        fragmentTransaction.commit()
    }

    private fun setNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(navHostFragmentId()) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun navigate(directions: NavDirections) = navController.navigate(directions)

    override fun navigateBack() = navController.popBackStack()
}