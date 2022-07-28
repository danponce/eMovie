package au.com.carsales.emovie.utils

import androidx.appcompat.widget.SearchView
import java.util.*

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class DelayedSimpleQueryTextListener(val queryAction : (String) -> Unit, val delayTime: Long = 300) : SearchView.OnQueryTextListener {

    var timer : Timer?= null

    override fun onQueryTextSubmit(query: String?): Boolean {

        delaySearch(query.orEmpty())

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        delaySearch(newText.orEmpty())

        return true
    }

    private fun delaySearch(query : String) {
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                queryAction.invoke(query)
            }
        }, delayTime)
    }
}