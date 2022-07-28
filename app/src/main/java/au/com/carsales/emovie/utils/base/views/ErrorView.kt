package au.com.carsales.emovie.utils.base.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import au.com.carsales.emovie.R
import au.com.carsales.emovie.databinding.ViewErrorBinding

class ErrorView(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    private val binding = ViewErrorBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        addView(binding.root)
        context.loadStyledAttributes(R.styleable.ErrorView, attrs) {
            binding.apply {
                setText(it.getString(R.styleable.ErrorView_text).orEmpty())
            }
        }
    }

    fun setText(value: String) {
        binding.errorTextView.text = value
        refresh()
    }

    fun refresh() {
        invalidate()
        requestLayout()
    }
}