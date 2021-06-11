package com.cstasenko.mixclouddiscover.repository

import com.cstasenko.mixclouddiscover.TestUtils
import com.cstasenko.mixclouddiscover.model.MixcloudApiResponseDto
import com.cstasenko.mixclouddiscover.model.MixcloudShow
import com.cstasenko.mixclouddiscover.service.MixcloudApiService
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MixcloudRepositoryImplTest {

    @MockK
    lateinit var apiService: MixcloudApiService

    lateinit var mixcloudRepository: MixcloudRepositoryImpl

    private val discoverResponse: MixcloudApiResponseDto
        get() = TestUtils.getLocalJsonFile(
            "mixcloud_discover_popular_shows_nts_response.json"
        )

    private val userShowsResponse: MixcloudApiResponseDto
        get() = TestUtils.getLocalJsonFile(
            "mixcloud_user_shows_search_response.json"
        )

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mixcloudRepository = MixcloudRepositoryImpl(apiService)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getTopShowsForTag_shouldReturnFlowWithDomainObject() = runBlocking {
        coEvery {
            apiService.getMostPopularShowsPerTag("nts", 5)
        } returns discoverResponse

        val actualFlow = mixcloudRepository.discoverTopShowsForTag("nts").toList()
        val expectedFlow = TestUtils.buildMixcloudShowDomainObjectsFlow().toList()

        assertThat(actualFlow).isEqualTo(expectedFlow)
        // assert that only one list is emitted in the flow
        assertThat(actualFlow.size).isEqualTo(1)
        // assert that returned object is of Domain Object - MixcloudShow - type
        assertThat(actualFlow.first().get(0)).isInstanceOf(MixcloudShow::class.java)
        // assert that we're getting 5 results
        assertThat(actualFlow.first().size).isEqualTo(5)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getLastTenUploadsPerUser_shouldReturnFlowWithDomainObject() = runBlocking {
        coEvery {
            apiService.getLastTenShowsPerUser(10)
        } returns userShowsResponse

        val actualFlow = mixcloudRepository.getLastTenUploadsForUser().toList()
        // assert that only one list is emitted in the flow
        assertThat(actualFlow.size).isEqualTo(1)
        // assert that returned object is of Domain Object - MixcloudShow - type
        assertThat(actualFlow.first().get(0)).isInstanceOf(MixcloudShow::class.java)
        // assert that we're getting 10 results
        assertThat(actualFlow.first().size).isEqualTo(10)
    }
}
