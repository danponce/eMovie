package au.com.carsales.emovie.utils.base.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import au.com.carsales.emovie.R
import au.com.carsales.emovie.databinding.ViewEmptyAllBinding
import au.com.carsales.emovie.databinding.ViewEmptyListBinding

class EmptyListView(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    private val binding = ViewEmptyListBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        addView(binding.root)
        context.loadStyledAttributes(R.styleable.EmptyListView, attrs) {
            binding.apply {
                setText(it.getString(R.styleable.EmptyListView_text).orEmpty())
            }
        }
    }

    fun setText(value: String) {
        binding.emptyListTextView.text = value
        refresh()
    }

    fun refresh() {
        invalidate()
        requestLayout()
    }
}