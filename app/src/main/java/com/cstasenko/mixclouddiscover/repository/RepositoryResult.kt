package com.cstasenko.mixclouddiscover.repository

import com.cstasenko.mixclouddiscover.model.MixcloudShow

sealed class RepositoryResult {
    data class Success(val data: List<MixcloudShow>) : RepositoryResult()
    object Error : RepositoryResult()
}
