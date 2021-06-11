package com.cstasenko.mixclouddiscover.view

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.cstasenko.mixclouddiscover.databinding.ViewCarouselPagerBinding

class CarouselPagerView : ConstraintLayout {
    private lateinit var viewPager: ViewPager2
    private lateinit var internalRecyclerView: RecyclerView
    private var totalItemCount: Int = 0

    private val carouselHandler: Handler = Handler(Looper.getMainLooper())

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
        val layoutInflater = LayoutInflater.from(context)
        val binding = ViewCarouselPagerBinding.inflate(layoutInflater, this)
        viewPager = binding.carousel
        internalRecyclerView = viewPager.getChildAt(0) as RecyclerView
    }

    fun <T : RecyclerView.ViewHolder> setAdapter(carouselAdapter: RecyclerView.Adapter<T>) {
        val carouselRunnable = Runnable { viewPager.currentItem = viewPager.currentItem + 1 }

        viewPager.apply {
            adapter = carouselAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            setPageTransformer(buildCompositePageTransformer())
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    carouselHandler.removeCallbacks(carouselRunnable)
                    carouselHandler.postDelayed(carouselRunnable, SCROLL_INTERVAL)
                }
            })
            setCurrentItem(1, false)
        }
        totalItemCount = carouselAdapter.itemCount

        internalRecyclerView.apply {
            addOnScrollListener(
                InfiniteScrollListener(
                    totalItemCount,
                    layoutManager as LinearLayoutManager
                )
            )
        }
    }

    private fun buildCompositePageTransformer(): CompositePageTransformer {
        return CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(16))
            addTransformer(ViewPager2.PageTransformer(transformFocusImage()))
        }
    }

    private fun transformFocusImage(): (page: View, position: Float) -> Unit = { page, position ->
        val rr: Float = 1 - Math.abs(position)
        page.scaleY = 0.85f + rr * 0.15f
        page.scaleX = 0.85f + rr * 0.15f
    }

    inner class InfiniteScrollListener(
        private val itemCount: Int,
        private val layoutManager: LinearLayoutManager
    ) : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val firstItemVisible = layoutManager.findFirstVisibleItemPosition()
            val lastItemVisible = layoutManager.findLastVisibleItemPosition()

            if (firstItemVisible == (itemCount - 1) && dx > 0) {
                recyclerView.scrollToPosition(1)
            } else if (lastItemVisible == 0 && dx < 0) {
                recyclerView.scrollToPosition(itemCount - 2)
            }
        }
    }

    companion object {
        const val SCROLL_INTERVAL: Long = 2000
        const val CAROUSEL_CARD_MARGIN: Int = 16
    }
}
