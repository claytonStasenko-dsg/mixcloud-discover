package com.cstasenko.mixclouddiscover.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.cstasenko.mixclouddiscover.R
import com.cstasenko.mixclouddiscover.adapter.MixcloudCarouselAdapter
import com.cstasenko.mixclouddiscover.databinding.FragmentUserShowsBinding
import com.cstasenko.mixclouddiscover.di.ApplicationComponentProvider
import com.cstasenko.mixclouddiscover.repository.MixcloudRepository
import com.cstasenko.mixclouddiscover.viewmodel.UserSearchState
import com.cstasenko.mixclouddiscover.viewmodel.UserShowsViewModel
import com.cstasenko.mixclouddiscover.viewmodel.viewModelBuilderFragmentScope
import javax.inject.Inject

class UserShowsFragment : Fragment(R.layout.fragment_user_shows) {

    @Inject
    lateinit var mixcloudRepository: MixcloudRepository

    private val userShowsViewModel: UserShowsViewModel by viewModelBuilderFragmentScope {
        UserShowsViewModel(
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
        val binding = FragmentUserShowsBinding.bind(view)

        userShowsViewModel.searchForMostRecentShowsByUser()
        observeUserShows(binding)
    }

    private fun observeUserShows(binding: FragmentUserShowsBinding) {
        userShowsViewModel.userShows.observe(viewLifecycleOwner, {
            handleUserSearchState(it, binding)
        })
    }

    private fun handleUserSearchState(
        userSearchState: UserSearchState,
        binding: FragmentUserShowsBinding
    ) {
        when (userSearchState) {
            is UserSearchState.Loading -> {
                binding.progressBar.showProgressBar()
            }

            is UserSearchState.OnDataReady -> {
                binding.progressBar.hideProgressBar()
                binding.carouselPager.visibility = View.VISIBLE
                binding.carouselPager.setAdapter(
                    MixcloudCarouselAdapter(userSearchState.response) { mixcloudShow ->
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setData(Uri.parse(mixcloudShow.link))
                    requireContext().startActivity(intent)
                })
            }
            is UserSearchState.OnError -> {
                // Error handling needed
            }
        }
    }
}
