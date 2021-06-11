package com.cstasenko.mixclouddiscover.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.cstasenko.mixclouddiscover.R
import com.cstasenko.mixclouddiscover.adapter.MixcloudCarouselAdapter
import com.cstasenko.mixclouddiscover.databinding.FragmentDemoBinding
import com.cstasenko.mixclouddiscover.di.ApplicationComponentProvider
import com.cstasenko.mixclouddiscover.repository.MixcloudRepository
import com.cstasenko.mixclouddiscover.viewmodel.DiscoverSearchState
import com.cstasenko.mixclouddiscover.viewmodel.DiscoverShowsViewModel
import com.cstasenko.mixclouddiscover.viewmodel.viewModelBuilderFragmentScope
import javax.inject.Inject

class DemoFragment : Fragment(R.layout.fragment_demo) {

    @Inject
    lateinit var mixcloudRepository: MixcloudRepository

    private val discoverViewModel: DiscoverShowsViewModel by viewModelBuilderFragmentScope {
        DiscoverShowsViewModel(
            mixcloudRepository
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as ApplicationComponentProvider)
            .provideApplicationComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDemoBinding.bind(view)

        binding.progressBar.showProgressBar()

        // also showing progress bar here due to initial lazy loading of list
        discoverViewModel.ntsTopShows.observe(viewLifecycleOwner, {
            handleDiscoverState(it, binding)
        })
    }

    private fun handleDiscoverState(
        discoverSearchState: DiscoverSearchState,
        binding: FragmentDemoBinding
    ) {
        when (discoverSearchState) {
            is DiscoverSearchState.Loading -> {
                binding.progressBar.showProgressBar()
            }
            is DiscoverSearchState.OnDataReady -> {
                binding.progressBar.hideProgressBar()
                binding.carouselPager.visibility = View.VISIBLE
                binding.carouselPager.setAdapter(
                    MixcloudCarouselAdapter(discoverSearchState.response) { mixcloudShow ->
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setData(Uri.parse(mixcloudShow.link))
                    requireContext().startActivity(intent)
                })
            }
            is DiscoverSearchState.OnError -> {
                // Error handling needed
            }
        }
    }
}
