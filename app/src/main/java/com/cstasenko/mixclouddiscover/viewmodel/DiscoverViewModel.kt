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
        mixcloudRepository.discoverTopShowsForTag(SEARCH_TERM_NTS).map {
            DiscoverSearchState.OnDataReady(buildExtraViews(it))
        }.asLiveData()
    }

    fun searchForTopShows(tag: String) {
        _topShows.value = DiscoverSearchState.Loading
        viewModelScope.launch {
            mixcloudRepository.discoverTopShowsForTag(tag).collect {
                _topShows.postValue(DiscoverSearchState.OnDataReady(buildExtraViews(it)))
            }
        }
    }

    private fun buildExtraViews(data: List<MixcloudShow>): List<MixcloudShow> {
        return listOf(
            data.last().copy(key = DUMMY_VIEW_ONE)) +
                data +
                listOf(
                    data.first().copy(key = DUMMY_VIEW_TWO))
    }

    companion object {
        const val SEARCH_TERM_NTS = "nts"
        const val DUMMY_VIEW_ONE = "faked1"
        const val DUMMY_VIEW_TWO = "faked2"
    }
}
