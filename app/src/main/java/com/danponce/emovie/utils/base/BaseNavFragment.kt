package com.danponce.emovie.utils.base

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator

/**
 * Created by Dan on 24, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
abstract class BaseNavFragment : Fragment() {

    private lateinit var navHelper : NavigationHelperListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        when(context) {
            is NavigationHelperListener -> { navHelper = context }
            else -> {
                throw RuntimeException(
                    "$context must implement NavigationHelperListener"
                )}

            }
    }

    protected fun navigate(directions: NavDirections, extras : FragmentNavigator.Extras?= null) {
        navHelper.navigate(directions, extras)
    }

    protected fun navigateBack() {
        navHelper.navigateBack()
    }
}