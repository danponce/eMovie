package com.danponce.emovie.utils

import java.util.*

/**
 * Created by Dan on 06, agosto, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 *
 * Helper for functionalities
 * related with languages
 */
object LanguageHelper {

    /**
     * Converts an ISO-639-1 Code
     * to display language
     *
     * @param isoCode       the ISO-639-1 code
     */
    fun getISOToLanguage(isoCode : String) : String {
        val locale = Locale(isoCode)
        return locale.displayLanguage
    }
}