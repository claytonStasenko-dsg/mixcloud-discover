package com.cstasenko.mixclouddiscover

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.cstasenko.mixclouddiscover.model.MixcloudApiResponseDto
import kotlinx.coroutines.flow.map

sealed class DiscoverSearchState {
    data class OnDataReady(val response: MixcloudApiResponseDto): DiscoverSearchState()
    object OnError : DiscoverSearchState()
}

class DiscoverMixesViewModel(private val mixcloudRepository: MixcloudRepository) : ViewModel() {

    val mixes: LiveData<DiscoverSearchState> by lazy {
        mixcloudRepository.getTopShows().map {
            if (it != null) {
                DiscoverSearchState.OnDataReady(it)
            } else {
                DiscoverSearchState.OnError
            }
        }.asLiveData()
    }
}