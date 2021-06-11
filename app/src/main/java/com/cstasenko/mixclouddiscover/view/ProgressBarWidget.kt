package com.cstasenko.mixclouddiscover.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.cstasenko.mixclouddiscover.databinding.WidgetProgressBarBinding

class ProgressBarWidget : ConstraintLayout {
    private lateinit var widgetProgressBinding: WidgetProgressBarBinding

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    private fun initView() {
        widgetProgressBinding = WidgetProgressBarBinding.inflate(LayoutInflater.from(context), this)

        widgetProgressBinding.root.setOnClickListener {
            // Prevent the view clicks while progress was shown
        }
        hideProgressBar()
    }

    fun showProgressBar() {
        widgetProgressBinding.root.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        widgetProgressBinding.root.visibility = View.GONE
    }
}
