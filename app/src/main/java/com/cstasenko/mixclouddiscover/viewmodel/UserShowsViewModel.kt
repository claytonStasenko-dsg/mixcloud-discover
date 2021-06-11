package com.cstasenko.mixclouddiscover.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cstasenko.mixclouddiscover.model.MixcloudShow
import com.cstasenko.mixclouddiscover.repository.MixcloudRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

sealed class UserSearchState {
    data class OnDataReady(val response: List<MixcloudShow>) : UserSearchState()
    object Loading : UserSearchState()
    object OnError : UserSearchState()
}

class UserShowsViewModel(private val mixcloudRepository: MixcloudRepository) : ViewModel() {
    private val _userShows: MutableLiveData<UserSearchState> = MutableLiveData()
    val userShows: LiveData<UserSearchState>
        get() = _userShows

    fun searchForMostRecentShowsByUser() {
        _userShows.value = UserSearchState.Loading
        viewModelScope.launch {
            mixcloudRepository.getLastTenUploadsForUser().collect {
                _userShows.postValue(UserSearchState.OnDataReady(buildExtraViews(it)))
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
        const val DUMMY_VIEW_ONE = "faked1"
        const val DUMMY_VIEW_TWO = "faked2"
    }
}
