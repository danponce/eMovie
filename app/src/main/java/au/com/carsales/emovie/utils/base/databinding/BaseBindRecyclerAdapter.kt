package au.com.carsales.emovie.utils.base.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Dan on 24, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 *
 * Used to stop writing onCreateViewHolder
 * and onBindViewHolder methods anymore!
 */
abstract class BaseBindRecyclerAdapter<T>(val clickHandler: BaseBindClickHandler<T>? = null) : RecyclerView.Adapter<BaseBindViewHolder<T>>(), BindableAdapter<List<T>?> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindViewHolder<T> {

        // Get inflater from ViewGroup
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)

        // Create the view holder with data binding
        val binding : ViewDataBinding = DataBindingUtil.inflate(inflater, viewType, parent, false)

        return BaseBindViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseBindViewHolder<T>, position: Int) {

        val item : T = getItemForPosition(position)
        holder.bind(item, clickHandler)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIfForPosition(position)
    }

    /**
     * Returns "Any" item
     * based on item position
     */
    protected abstract fun getItemForPosition(position : Int) : T

    /**
     * Returns the layout id based
     * on the position of the item
     */
    protected abstract fun getLayoutIfForPosition(position : Int) : Int
}