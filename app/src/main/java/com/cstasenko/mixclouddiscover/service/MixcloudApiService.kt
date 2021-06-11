package com.cstasenko.mixclouddiscover.service

import com.cstasenko.mixclouddiscover.model.MixcloudApiResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MixcloudApiService {

    @GET("/discover/{keyword}/popular/?limit=5")
    suspend fun getMostPopularShowsPerTag(
        @Path(value = "keyword") keyword: String
    ): MixcloudApiResponseDto

    @GET("thelotradio/cloudcasts/?limit=10")
    suspend fun getLastTenShowsPerUser(): MixcloudApiResponseDto
}
