package com.cstasenko.mixclouddiscover.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.motion.widget.MotionLayout
import com.cstasenko.mixclouddiscover.databinding.CardMotionCarouselBinding
import com.cstasenko.mixclouddiscover.loadImage

class MotionCarouselCard : MotionLayout {
    private var _binding: CardMotionCarouselBinding? = null
    private val binding: CardMotionCarouselBinding
        get() = _binding!!

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        _binding = CardMotionCarouselBinding.inflate(layoutInflater, this)
    }

    fun setCardContent(imageUrl: String, imageText: String) {
        binding.coverImage.loadImage(imageUrl)
        binding.coverTitle.text = imageText
    }
}