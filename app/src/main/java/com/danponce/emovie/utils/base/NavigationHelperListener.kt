package com.danponce.emovie.utils.base

import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator

/**
 * Created by Dan on 24, julio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
interface NavigationHelperListener {

    fun navigate(directions : NavDirections, extras: FragmentNavigator.Extras? = null)

    fun navigateBack() : Boolean
}