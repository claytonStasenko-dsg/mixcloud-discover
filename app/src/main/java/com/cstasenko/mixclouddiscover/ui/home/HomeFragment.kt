package com.cstasenko.mixclouddiscover.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cstasenko.mixclouddiscover.DiscoverMixesViewModel
import com.cstasenko.mixclouddiscover.DiscoverSearchState
import com.cstasenko.mixclouddiscover.MixcloudRepository
import com.cstasenko.mixclouddiscover.R
import com.cstasenko.mixclouddiscover.adapter.MixcloudListAdapter
import com.cstasenko.mixclouddiscover.databinding.FragmentHomeBinding
import com.cstasenko.mixclouddiscover.di.ApplicationComponentProvider
import com.cstasenko.mixclouddiscover.util.HorizontalSpacingDecoration
import com.cstasenko.mixclouddiscover.viewModelBuilderFragmentScope
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!

    private val mixcloudShowAdapter: MixcloudListAdapter = MixcloudListAdapter()
    private lateinit var linearLayoutManager: LinearLayoutManager

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
        _binding = FragmentHomeBinding.bind(view)
        setupRecyclerCarousel()
    }

    private fun setupRecyclerCarousel() {
        linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val spacing = resources.getDimensionPixelSize(R.dimen.carousel_spacing)
        binding.carousel.apply {
            adapter = mixcloudShowAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(HorizontalSpacingDecoration(spacing))
        }
        observeMixcloudShows()
    }

    private fun observeMixcloudShows() {
        discoverViewModel.mixes.observe(viewLifecycleOwner, {
            handleDiscoverState(it)
        })
    }

    private fun handleDiscoverState(discoverSearchState: DiscoverSearchState) {
        when (discoverSearchState) {
            is DiscoverSearchState.OnDataReady -> {
                mixcloudShowAdapter.submitList(discoverSearchState.response)
            }
            is DiscoverSearchState.OnError -> {
                //TODO error
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}