package au.com.carsales.emovie.utils.base.databinding

import android.view.View

/**
 * Created by Dan on 25, June, 2022
 * Copyright (c) 2019 Carsales. All rights reserved.
 *
 * In case it's necessary to implement
 * a click handler in BaseBindRecyclerAdapter
 * this interface is needed to be implemented
 * in the class so the click action can successfully work
 */
interface BaseBindClickHandler<T> {

    fun onClickView(view: View, item : T)

}