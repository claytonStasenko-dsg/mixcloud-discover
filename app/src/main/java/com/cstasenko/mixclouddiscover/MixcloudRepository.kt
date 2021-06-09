package com.cstasenko.mixclouddiscover

import com.cstasenko.mixclouddiscover.model.MixcloudApiResponseDto
import com.cstasenko.mixclouddiscover.model.MixcloudShow
import kotlinx.coroutines.flow.Flow

interface MixcloudRepository {

    fun getTopShows(): Flow<List<MixcloudShow>?>
}