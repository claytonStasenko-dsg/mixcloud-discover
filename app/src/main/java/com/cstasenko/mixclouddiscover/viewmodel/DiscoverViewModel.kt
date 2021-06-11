package com.cstasenko.mixclouddiscover.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.cstasenko.mixclouddiscover.model.MixcloudShow
import com.cstasenko.mixclouddiscover.repository.MixcloudRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

sealed class DiscoverSearchState {
    data class OnDataReady(val response: List<MixcloudShow>) : DiscoverSearchState()
    object OnError : DiscoverSearchState()
    object Loading : DiscoverSearchState()
}

class DiscoverShowsViewModel(private val mixcloudRepository: MixcloudRepository) : ViewModel() {
    private val _topShows: MutableLiveData<DiscoverSearchState> = MutableLiveData()
    val topShows: LiveData<DiscoverSearchState>
        get() = _topShows

    val ntsTopShows: LiveData<DiscoverSearchState> by lazy {
        DiscoverSearchState.Loading
        mixcloudRepository.discoverTopShowsForNts().map {
            if (it != null) {
                DiscoverSearchState.OnDataReady(buildExtraViews(it))
            } else {
                DiscoverSearchState.OnError
            }
        }.asLiveData()
    }

    fun searchForTopShows(tag: String) {
        _topShows.value = DiscoverSearchState.Loading
        viewModelScope.launch {
            mixcloudRepository.discoverTopShowsForTag(tag).collect {
                if (it != null) {
                    _topShows.postValue(DiscoverSearchState.OnDataReady(buildExtraViews(it)))
                } else {
                    _topShows.postValue(DiscoverSearchState.OnError)
                }
            }
        }
    }

    private fun buildExtraViews(data: List<MixcloudShow>): List<MixcloudShow> {
        return listOf(
            data.last().copy(key = "faked1")) +
                data +
                listOf(
                    data.first().copy(key = "faked2"))
    }
}
