package com.cstasenko.mixclouddiscover.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.helper.widget.Carousel
import androidx.fragment.app.Fragment
import com.cstasenko.mixclouddiscover.R
import com.cstasenko.mixclouddiscover.databinding.FragmentHomeBinding
import com.cstasenko.mixclouddiscover.di.ApplicationComponentProvider
import com.cstasenko.mixclouddiscover.loadImage
import com.cstasenko.mixclouddiscover.model.MixcloudApiResponseDto
import com.cstasenko.mixclouddiscover.repository.MixcloudRepository
import com.cstasenko.mixclouddiscover.viewmodel.DiscoverMixesViewModel
import com.cstasenko.mixclouddiscover.viewmodel.DiscoverSearchState
import com.cstasenko.mixclouddiscover.viewmodel.viewModelBuilderFragmentScope
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    @Inject
    lateinit var mixcloudRepository: MixcloudRepository

    private val discoverViewModel: DiscoverMixesViewModel by viewModelBuilderFragmentScope {
        DiscoverMixesViewModel(
            mixcloudRepository
        )
    }

    var images = intArrayOf(
        R.drawable.theo_parrish_mixcloud_medium,
        R.drawable.theo_parrish_mixcloud_medium,
        R.drawable.theo_parrish_mixcloud_medium,
        R.drawable.theo_parrish_mixcloud_medium,
        R.drawable.theo_parrish_mixcloud_medium,
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as ApplicationComponentProvider).provideApplicationComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)

//        discoverViewModel.mixes.observe(viewLifecycleOwner, {
//            handleDiscoverState(it, binding)
//        })

        binding.carousel.setAdapter(object: Carousel.Adapter {
            override fun count(): Int {
                return images.size
            }

            override fun populate(view: View?, index: Int) {
                if (view is ImageView) {
                    view.setImageResource(images[index])
                }
            }

            override fun onNewItem(index: Int) {
                //dont do anything special
            }

        })
    }

    private fun handleDiscoverState(discoverSearchState: DiscoverSearchState, binding: FragmentHomeBinding) {
        when (discoverSearchState) {
            is DiscoverSearchState.OnDataReady -> {
                setCarouselAdapter(discoverSearchState.response, binding)
//                binding.textHome.text = discoverSearchState.response.data[0].name
//                binding.mixImage.loadImage(discoverSearchState.response.data[0].pictures.medium)
            }
            is DiscoverSearchState.OnError -> {
                //TODO error
            }
        }
    }

    private fun setCarouselAdapter(result: MixcloudApiResponseDto, binding: FragmentHomeBinding) {
        binding.carousel.setAdapter(object: Carousel.Adapter {
            override fun count(): Int {
                val count = result.data.size
                return count
            }

            override fun populate(view: View?, index: Int) {
                if (view is ImageView) {
                    view.loadImage(result.data[index].pictures.medium)
                }
            }

            override fun onNewItem(index: Int) {
                TODO("Not yet implemented")
            }

        })
    }

}