package au.com.carsales.emovie.utils.base

import androidx.navigation.NavDirections

/**
 * Created by Dan on 24, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
interface NavigationHelperListener {

    fun navigate(directions : NavDirections)

    fun navigateBack() : Boolean
}