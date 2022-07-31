package au.com.carsales.emovie.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Dan on 31, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 * 
 * Used for date related
 * util functionality
 */
object DateUtils {

    const val yyyyMMddFormat = "yyyy-MM-dd"

    fun getActualDate(format : String) : String {
        return SimpleDateFormat(format, Locale.getDefault()).format(Date())
    }
}