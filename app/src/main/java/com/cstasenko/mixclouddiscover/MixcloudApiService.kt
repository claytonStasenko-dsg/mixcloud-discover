package com.cstasenko.mixclouddiscover

import com.cstasenko.mixclouddiscover.model.MixcloudApiResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MixcloudApiService {

    @GET("/discover/{keyword}/popular/?limit=5")
    suspend fun getMostPopularShowsPerTag(@Path(value = "keyword") keyword: String): Response<MixcloudApiResponseDto>

}