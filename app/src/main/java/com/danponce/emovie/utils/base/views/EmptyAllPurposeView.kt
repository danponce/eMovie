package com.danponce.emovie.utils.base.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.danponce.emovie.R
import com.danponce.emovie.databinding.ViewEmptyAllBinding

class EmptyAllPurposeView(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    private val binding = ViewEmptyAllBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        addView(binding.root)
        context.loadStyledAttributes(R.styleable.EmptyAllView, attrs) {
            binding.apply {
                setText(it.getString(R.styleable.EmptyAllView_text).orEmpty())
            }
        }
    }

    fun setText(value: String) {
        binding.emptyAllTextView.text = value
        refresh()
    }

    fun refresh() {
        invalidate()
        requestLayout()
    }
}