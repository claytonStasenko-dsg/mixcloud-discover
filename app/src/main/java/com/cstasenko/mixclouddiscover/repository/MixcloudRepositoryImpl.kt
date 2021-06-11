package com.cstasenko.mixclouddiscover.repository

import com.cstasenko.mixclouddiscover.model.MixcloudShow
import com.cstasenko.mixclouddiscover.model.User
import com.cstasenko.mixclouddiscover.service.MixcloudApiService
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class MixcloudRepositoryImpl @Inject constructor(
    private val apiService: MixcloudApiService
) : MixcloudRepository {

    override fun discoverTopShowsForTag(tag: String): Flow<List<MixcloudShow>> {
        return flow {
            emit(apiService.getMostPopularShowsPerTag(tag, DISCOVER_RESULT_LIMIT))
        }.map { response ->
            response.data.map {
                MixcloudShow(
                    key = it.key,
                    name = it.name,
                    link = it.url,
                    imageUrl = it.pictures.extraLarge,
                    user = User(
                        userName = it.user.name,
                        userAvatarUrl = it.user.pictures.mediumMobile
                    )
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getLastTenUploadsForUser(): Flow<List<MixcloudShow>> {
        return flow {
            emit(apiService.getLastTenShowsPerUser(USER_SHOWS_RESULT_LIMIT))
        }.map { response ->
            response.data.map {
                MixcloudShow(
                    key = it.key,
                    name = it.name,
                    link = it.url,
                    imageUrl = it.pictures.extraLarge,
                    user = User(
                        userName = it.user.name,
                        userAvatarUrl = it.user.pictures.mediumMobile
                    )
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        const val DISCOVER_RESULT_LIMIT = 5
        const val USER_SHOWS_RESULT_LIMIT = 10
    }
}
