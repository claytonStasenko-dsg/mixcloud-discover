package com.cstasenko.mixclouddiscover.repository

import com.cstasenko.mixclouddiscover.model.MixcloudShow
import kotlinx.coroutines.flow.Flow

interface MixcloudRepository {
    fun discoverTopShowsForTag(tag: String): Flow<List<MixcloudShow>>
    fun discoverTopShowsForNts(): Flow<List<MixcloudShow>>
    fun getLastTenUploadsForUser(): Flow<List<MixcloudShow>>
}
