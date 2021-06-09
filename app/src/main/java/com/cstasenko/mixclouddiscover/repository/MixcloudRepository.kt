package com.cstasenko.mixclouddiscover.repository

import com.cstasenko.mixclouddiscover.model.MixcloudApiResponseDto
import kotlinx.coroutines.flow.Flow

interface MixcloudRepository {

    fun getTopShows(): Flow<MixcloudApiResponseDto?>
}