package com.cstasenko.mixclouddiscover.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.cstasenko.mixclouddiscover.DiscoverMixesViewModel
import com.cstasenko.mixclouddiscover.DiscoverSearchState
import com.cstasenko.mixclouddiscover.MixcloudRepository
import com.cstasenko.mixclouddiscover.R
import com.cstasenko.mixclouddiscover.databinding.FragmentHomeBinding
import com.cstasenko.mixclouddiscover.di.ApplicationComponentProvider
import com.cstasenko.mixclouddiscover.viewModelBuilderFragmentScope
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
                binding.textHome.text = discoverSearchState.response.data[0].name
                binding.mixImage.loadImage(discoverSearchState.response.data[0].pictures.medium)
            }
            is DiscoverSearchState.OnError -> {
                //TODO error
            }
        }
    }

    fun ImageView.loadImage(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
            .into(this)
    }
}