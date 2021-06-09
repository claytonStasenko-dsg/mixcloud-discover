package com.cstasenko.mixclouddiscover.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.cstasenko.mixclouddiscover.repository.MixcloudRepository
import com.cstasenko.mixclouddiscover.R
import com.cstasenko.mixclouddiscover.databinding.FragmentHomeBinding
import com.cstasenko.mixclouddiscover.di.ApplicationComponentProvider
import com.cstasenko.mixclouddiscover.loadImage
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as ApplicationComponentProvider).provideApplicationComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)

        discoverViewModel.mixes.observe(viewLifecycleOwner, {
            handleDiscoverState(it, binding)
        })
    }

    private fun handleDiscoverState(discoverSearchState: DiscoverSearchState, binding: FragmentHomeBinding) {
        when (discoverSearchState) {
            is DiscoverSearchState.OnDataReady -> {
                binding.textHome.text = discoverSearchState.response[0].name
                binding.mixImage.loadImage(discoverSearchState.response[0].imageUrlMedium)
            }
            is DiscoverSearchState.OnError -> {
                //TODO error
            }
        }
    }
}