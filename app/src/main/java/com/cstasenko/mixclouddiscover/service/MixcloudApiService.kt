package com.cstasenko.mixclouddiscover.service

import com.cstasenko.mixclouddiscover.model.MixcloudApiResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MixcloudApiService {

    @GET(DISCOVER_PATH)
    suspend fun getMostPopularShowsPerTag(
        @Path(value = "keyword") keyword: String,
        @Query("limit") limit: Int
    ): MixcloudApiResponseDto

    @GET(USER_SHOW_PATH)
    suspend fun getLastTenShowsPerUser(
        @Query("limit") limit: Int
    ): MixcloudApiResponseDto

    companion object {
        const val DISCOVER_PATH = "/discover/{keyword}/popular/"
        const val USER_SHOW_PATH = "/thelotradio/cloudcasts/"
    }
}
