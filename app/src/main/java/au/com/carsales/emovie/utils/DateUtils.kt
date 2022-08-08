package au.com.carsales.emovie.utils

import java.text.ParseException
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
    const val yyyyFormat = "yyyy"

    fun getActualDate(format : String) : String {
        return SimpleDateFormat(format, Locale.getDefault()).format(Date())
    }

    fun getYearFromDate(dateInString : String, dateFormat: String) : String {
        var format = SimpleDateFormat(dateFormat, Locale.getDefault())
        var date : Date? = null

        try {
            date = format.parse(dateInString)
        } catch (e: ParseException) {}

        return when (date) {
            null -> ""
            else -> SimpleDateFormat(yyyyFormat, Locale.getDefault()).format(date)
        }
    }

    fun getActualDateMinusMonths(format : String, monthsQuantity : Int) : String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, monthsQuantity)
        val dateChanged = calendar.time
        return SimpleDateFormat(format, Locale.getDefault()).format(dateChanged)
    }
}