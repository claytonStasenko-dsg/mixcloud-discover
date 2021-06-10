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

    override fun getTopShows(): Flow<List<MixcloudShow>?> {
        return flow {
            emit(apiService.getMostPopularShowsPerTag("nts"))
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
}
