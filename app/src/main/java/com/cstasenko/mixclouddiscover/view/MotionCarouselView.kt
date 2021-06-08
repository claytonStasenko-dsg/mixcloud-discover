package com.cstasenko.mixclouddiscover.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.helper.widget.Carousel
import androidx.constraintlayout.motion.widget.MotionLayout
import com.cstasenko.mixclouddiscover.databinding.CardMotionCarouselBinding
import com.cstasenko.mixclouddiscover.databinding.ViewMotionCarouselBinding
import com.cstasenko.mixclouddiscover.model.MixcloudApiResponseDto

class MotionCarouselView : MotionLayout {
    private var _binding: ViewMotionCarouselBinding? = null
    private val binding: ViewMotionCarouselBinding
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
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        _binding = ViewMotionCarouselBinding.inflate(layoutInflater, this)
    }

    fun setImages(result: MixcloudApiResponseDto) {
//        binding.card0.setCardContent(result.data[0].name, result.data[0].pictures.medium)
//        binding.card1.setCardContent(result.data[1].name, result.data[1].pictures.medium)
//        binding.card2.setCardContent(result.data[2].name, result.data[2].pictures.medium)
//        binding.card3.setCardContent(result.data[3].name, result.data[3].pictures.medium)
//        binding.card4.setCardContent(result.data[4].name, result.data[4].pictures.medium)

        binding.carousel.setAdapter(object: Carousel.Adapter {
            override fun count(): Int {
                return result.data.size
            }

            override fun populate(view: View?, index: Int) {
                if (view is MotionCarouselCard) {
                    view.setCardContent(result.data[index].name, result.data[index].pictures.medium)
                }
            }

            override fun onNewItem(index: Int) {
                TODO("Not yet implemented")
            }

        })
    }
}