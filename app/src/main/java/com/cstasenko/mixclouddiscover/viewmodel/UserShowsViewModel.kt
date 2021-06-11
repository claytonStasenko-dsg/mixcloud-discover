package com.cstasenko.mixclouddiscover.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.cstasenko.mixclouddiscover.model.MixcloudShow
import com.cstasenko.mixclouddiscover.repository.MixcloudRepository
import kotlinx.coroutines.flow.map

sealed class UserSearchState {
    data class OnDataReady(val data: List<MixcloudShow>) : UserSearchState()
    object Loading : UserSearchState()
    object OnError : UserSearchState()
}

class UserShowsViewModel(private val mixcloudRepository: MixcloudRepository) : ViewModel() {
    val userShows: LiveData<UserSearchState> by lazy {
        UserSearchState.Loading
        mixcloudRepository.getLastTenUploadsForUser().map {
            if (it != null) {
                UserSearchState.OnDataReady(buildExtraViews(it))
            } else {
                UserSearchState.OnError
            }
        }.asLiveData()
    }

    private fun buildExtraViews(data: List<MixcloudShow>): List<MixcloudShow> {
        return listOf(
            data.last().copy(key = "faked1")) +
                data +
                listOf(
                    data.first().copy(key = "faked2"))
    }
}
