package com.cstasenko.mixclouddiscover.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.cstasenko.mixclouddiscover.R
import com.cstasenko.mixclouddiscover.adapter.MixcloudCarouselAdapter
import com.cstasenko.mixclouddiscover.databinding.FragmentDiscoverBinding
import com.cstasenko.mixclouddiscover.di.ApplicationComponentProvider
import com.cstasenko.mixclouddiscover.repository.MixcloudRepository
import com.cstasenko.mixclouddiscover.viewmodel.DiscoverSearchState
import com.cstasenko.mixclouddiscover.viewmodel.DiscoverShowsViewModel
import com.cstasenko.mixclouddiscover.viewmodel.viewModelBuilderFragmentScope
import javax.inject.Inject

class DiscoverFragment : Fragment(R.layout.fragment_discover) {

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
        val binding = FragmentDiscoverBinding.bind(view)

        setSearchTextChangedListener(binding)
        setSearchbuttonClickListener(binding)
        setSearchEditTextClickListener(binding)
    }

    private fun setSearchTextChangedListener(binding: FragmentDiscoverBinding) {
        binding.searchText.addTextChangedListener { editable ->
            editable?.let {
                if (it.isNotEmpty()) {
                    binding.searchButton.isEnabled = true
                }
            }
        }
    }

    private fun setSearchbuttonClickListener(binding: FragmentDiscoverBinding) {
        binding.searchButton.setOnClickListener {
            discoverViewModel.searchForTopShows(binding.searchText.text.toString())
            observeSearch(binding)
            hideKeyboard(it)
            binding.searchText.clearFocus()
        }
    }

    private fun setSearchEditTextClickListener(binding: FragmentDiscoverBinding) {
        binding.searchTextLayout.setOnClickListener {
            binding.searchText.requestFocus()
        }
    }

    private fun observeSearch(binding: FragmentDiscoverBinding) {
        discoverViewModel.topShows.observe(
            viewLifecycleOwner,
            {
                handleDiscoverState(it, binding)
            }
        )
    }

    private fun handleDiscoverState(state: DiscoverSearchState, binding: FragmentDiscoverBinding) {
        when (state) {
            is DiscoverSearchState.Loading -> {
                if (binding.carouselPager.isVisible) View.GONE
                binding.progressBar.showProgressBar()
            }
            is DiscoverSearchState.OnDataReady -> {
                binding.progressBar.hideProgressBar()
                binding.carouselPager.visibility = View.VISIBLE
                binding.carouselPager.setAdapter(
                    MixcloudCarouselAdapter(state.response) { mixcloudShow ->
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(mixcloudShow.link)
                    requireContext().startActivity(intent)
                })
            }
            is DiscoverSearchState.OnError -> {
                // needs error handling
            }
        }
    }

    private fun hideKeyboard(view: View?) {
        (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?)?.apply {
            hideSoftInputFromWindow(view?.windowToken, 0)
        }
    }
}
