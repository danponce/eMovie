package au.com.carsales.emovie.utils.base.databinding

import android.view.View

/**
 * Created by Dan on 24, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 *
 * Base recycler view adapter with
 * data binding that uses only one layout
 */
class SingleLayoutBindRecyclerAdapter<T>(private val layoutId: Int, var list: List<T?>?, clickHandler: BaseBindClickHandler<T>? = null) : BaseBindRecyclerAdapter<T>(clickHandler) {

    constructor(layoutId: Int, list: List<T?>?) : this(
        layoutId,
        list,
        object : BaseBindClickHandler<T> {
            override fun onClickView(view: View, item: T) { }
        })

    constructor(layoutId: Int, list: List<T?>?, clickHandler: ((view: View, item: T) -> Unit)? = null) : this(
        layoutId,
        list,
        object : BaseBindClickHandler<T> {
            override fun onClickView(view: View, item: T) {
                clickHandler?.invoke(view, item)
            }
        })

    override fun getLayoutIfForPosition(position: Int): Int {
        return layoutId
    }

    override fun getItemForPosition(position: Int): T {
        return list?.get(position)!!
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun setData(data: List<T>?) {
        list = data
        notifyDataSetChanged()
    }
}