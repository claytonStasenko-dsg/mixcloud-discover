package com.cstasenko.mixclouddiscover.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.cstasenko.mixclouddiscover.model.MixcloudApiResponseDto
import com.cstasenko.mixclouddiscover.model.MixcloudShow
import com.cstasenko.mixclouddiscover.repository.MixcloudRepository
import kotlinx.coroutines.flow.map

sealed class DiscoverSearchState {
    data class OnDataReady(val response: List<MixcloudShow>): DiscoverSearchState()
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

    private fun buildExtraViews(data: List<MixcloudShow>): List<MixcloudShow> {
        return listOf(data.last().copy(key = "faked1")) + data + listOf(data.first().copy(key = "faked2"))
    }
}