package com.cstasenko.mixclouddiscover

import com.cstasenko.mixclouddiscover.model.MixcloudApiResponseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MixcloudRepositoryImpl @Inject constructor(
    private val apiService: MixcloudApiService
): MixcloudRepository {

    override fun getTopShows(): Flow<MixcloudApiResponseDto?> {
        return flow {
            emit(apiService.getMostPopularShowsPerTag("nts").body())
        }
    }


}