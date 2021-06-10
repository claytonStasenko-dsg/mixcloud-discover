package com.cstasenko.mixclouddiscover.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.cstasenko.mixclouddiscover.repository.MixcloudRepository
import com.cstasenko.mixclouddiscover.R
import com.cstasenko.mixclouddiscover.adapter.MixcloudCarouselAdapter
import com.cstasenko.mixclouddiscover.adapter.MixcloudShowAdapter
import com.cstasenko.mixclouddiscover.databinding.FragmentHomeBinding
import com.cstasenko.mixclouddiscover.di.ApplicationComponentProvider
import com.cstasenko.mixclouddiscover.loadImage
import com.cstasenko.mixclouddiscover.model.MixcloudShow
import com.cstasenko.mixclouddiscover.viewmodel.DiscoverMixesViewModel
import com.cstasenko.mixclouddiscover.viewmodel.DiscoverSearchState
import com.cstasenko.mixclouddiscover.viewmodel.viewModelBuilderFragmentScope
import java.util.*
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    @Inject
    lateinit var mixcloudRepository: MixcloudRepository

    private lateinit var viewPager: ViewPager2
    private lateinit var shows: List<MixcloudShow>
    private lateinit var showAdapter: MixcloudShowAdapter
    private val carouselHandler = Handler()

    private val discoverViewModel: DiscoverMixesViewModel by viewModelBuilderFragmentScope {
        DiscoverMixesViewModel(
            mixcloudRepository
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as ApplicationComponentProvider).provideApplicationComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)

//        viewPager = binding.carousel

        discoverViewModel.mixes.observe(viewLifecycleOwner, {
            handleDiscoverState(it, binding)
        })
//
        val carouselRunnable = Runnable { binding.carousel.currentItem = binding.carousel.currentItem + 1 }
//        val infiniteRunnable = Runnable {
//            showAdapter.submitList(shows.map {
//                it.copy(
//                    key = "mixcloudddd"
//                )
//            })
//        }
//
//        showAdapter = MixcloudShowAdapter {
//           //nothing
//        }
//
//        binding.carousel.apply {
//
//            adapter = showAdapter
//            clipToPadding = false
//            clipChildren = false
//            offscreenPageLimit = 3
//            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
//            setPageTransformer(buildCompositePageTransformer())
//            registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
//                override fun onPageSelected(position: Int) {
//                    super.onPageSelected(position)
//                    carouselHandler.removeCallbacks(carouselRunnable)
//                    carouselHandler.postDelayed(carouselRunnable, 3000)
//                }
//            })
//        }
    }

    private fun buildCompositePageTransformer(): CompositePageTransformer {
        return CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
            addTransformer(ViewPager2.PageTransformer(transformFocusImage()))
        }
    }

    private fun transformFocusImage(): (page: View, position: Float) -> Unit = { page, position ->
        val rr: Float = 1 - Math.abs(position)
        page.scaleY = 0.85f + rr * 0.15f
    }

    private fun handleDiscoverState(discoverSearchState: DiscoverSearchState, binding: FragmentHomeBinding) {
        when (discoverSearchState) {
            is DiscoverSearchState.OnDataReady -> {
                binding.carouselPager.setAdapter(MixcloudCarouselAdapter(discoverSearchState.response) { mixcloudShow ->
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setData(Uri.parse(mixcloudShow.link))
                    requireContext().startActivity(intent)
                })

//                shows = discoverSearchState.response
//                showAdapter.submitList(shows)
            }
            is DiscoverSearchState.OnError -> {
                //TODO error
            }
        }
    }
}