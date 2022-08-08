package com.danponce.emovie.utils.base.databinding

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.danponce.emovie.BR

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2019 Carsales. All rights reserved.
 */
class BaseBindViewHolder<T>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item : T, clickHandler: BaseBindClickHandler<T>? = null) {

        binding.setVariable(BR.item, item)

        if(clickHandler != null)
            binding.setVariable(BR.clickHandler, clickHandler)

        binding.executePendingBindings()
    }
}