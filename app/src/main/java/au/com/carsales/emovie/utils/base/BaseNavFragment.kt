package au.com.carsales.emovie.utils.base

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections

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

    protected fun navigate(directions: NavDirections) {
        navHelper.navigate(directions)
    }

    protected fun navigateBack() {
        navHelper.navigateBack()
    }
}