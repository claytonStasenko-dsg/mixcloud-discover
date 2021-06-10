package com.cstasenko.mixclouddiscover.repository

import com.cstasenko.mixclouddiscover.JsonUtil
import com.cstasenko.mixclouddiscover.model.MixcloudApiResponseDto
import com.cstasenko.mixclouddiscover.model.MixcloudShow
import com.cstasenko.mixclouddiscover.model.User
import com.cstasenko.mixclouddiscover.service.MixcloudApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import java.io.InputStream

class MixcloudRepositoryImplTest {

    @MockK
    lateinit var apiService: MixcloudApiService

    lateinit var mixcloudRepository: MixcloudRepositoryImpl

    private val mixcloudDiscoverResponse: MixcloudApiResponseDto
        get() = JsonUtil.getLocalJsonFile(
            "mixcloud_discover_popular_shows_nts_response.json"
        )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mixcloudRepository = MixcloudRepositoryImpl(apiService)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getTopShows_shouldReturnFlowWithDomainObject() = runBlocking {
        coEvery {
            apiService.getMostPopularShowsPerTag("nts")
        } returns mixcloudDiscoverResponse

        val actualFlow = mixcloudRepository.getTopShows().toList()
        val expectedFlow = buildMixcloudShowDomainObjectsFlow().toList()

        assertThat(actualFlow).isEqualTo(expectedFlow)
        //assert that only one list is emitted in the flow
        assertThat(actualFlow.size).isEqualTo(1)
        //assert that returned object is of Domain Object - MixcloudShow - type
        assertThat(actualFlow.first()?.get(0)).isInstanceOf(MixcloudShow::class.java)
    }

    private fun buildMixcloudShowDomainObjectsFlow(): Flow<List<MixcloudShow>> {
        return flow {
            emit(
                listOf(
                    MixcloudShow(
                        key = "/NTSRadio/theo-parrish-presents-eargoggles-6-hour-mix-nts-10-22nd-april-2021/",
                        name = "Theo Parrish Presents: eargoggles (6 Hour Mix) - NTS 10 - 22nd April 2021",
                        link = "https://www.mixcloud.com/NTSRadio/theo-parrish-presents-eargoggles-6-hour-mix-nts-10-22nd-april-2021/",
                        imageUrl = "https://thumbnailer.mixcloud.com/unsafe/600x600/extaudio/c/c/2/3/d4d1-248b-4294-b71f-4285d9625a9f",
                        User(
                            userName = "NTS Radio",
                            userAvatarUrl = "https://thumbnailer.mixcloud.com/unsafe/80x80/profile/6/c/6/2/4cae-6e61-411c-adb6-9d9cd3e9112c.jpg",
                        )
                    ),
                    MixcloudShow(
                        key = "/NTSRadio/jamie-xx-2nd-april-2021/",
                        name = "Jamie XX - 2nd April 2021",
                        link = "https://www.mixcloud.com/NTSRadio/jamie-xx-2nd-april-2021/",
                        imageUrl = "https://thumbnailer.mixcloud.com/unsafe/600x600/extaudio/6/d/b/e/fdae-19eb-40c3-9d09-acf40a85d17b",
                        User(
                            userName = "NTS Radio",
                            userAvatarUrl = "https://thumbnailer.mixcloud.com/unsafe/80x80/profile/6/c/6/2/4cae-6e61-411c-adb6-9d9cd3e9112c.jpg",
                        )
                    ),
                    MixcloudShow(
                        key = "/NTSRadio/black-classical-history-of-spiritual-jazz-part-1/",
                        name = "Black Classical - History of Spiritual Jazz Part 1",
                        link = "https://www.mixcloud.com/NTSRadio/black-classical-history-of-spiritual-jazz-part-1/",
                        imageUrl = "https://thumbnailer.mixcloud.com/unsafe/600x600/extaudio/3/6/4/4/8f0f-6d80-40cb-b405-7b62ca77bfdd",
                        User(
                            userName = "NTS Radio",
                            userAvatarUrl = "https://thumbnailer.mixcloud.com/unsafe/80x80/profile/6/c/6/2/4cae-6e61-411c-adb6-9d9cd3e9112c.jpg",
                        )
                    ),
                    MixcloudShow(
                        key = "/NTSRadio/dj-nobu-26th-february-2021/",
                        name = "DJ Nobu - 26th February 2021",
                        link = "https://www.mixcloud.com/NTSRadio/dj-nobu-26th-february-2021/",
                        imageUrl = "https://thumbnailer.mixcloud.com/unsafe/600x600/extaudio/6/6/9/c/b62d-842b-449f-b1c5-edc2f452313f",
                        User(
                            userName = "NTS Radio",
                            userAvatarUrl = "https://thumbnailer.mixcloud.com/unsafe/80x80/profile/6/c/6/2/4cae-6e61-411c-adb6-9d9cd3e9112c.jpg",
                        )
                    ),
                    MixcloudShow(
                        key = "/NTSRadio/laurel-halo-11th-january-2021/",
                        name = "Awe w/ Laurel Halo  - 11th January 2021",
                        link = "https://www.mixcloud.com/NTSRadio/laurel-halo-11th-january-2021/",
                        imageUrl = "https://thumbnailer.mixcloud.com/unsafe/600x600/extaudio/1/8/7/a/d146-0021-401d-86d0-31c2a9591f40",
                        User(
                            userName = "NTS Radio",
                            userAvatarUrl = "https://thumbnailer.mixcloud.com/unsafe/80x80/profile/6/c/6/2/4cae-6e61-411c-adb6-9d9cd3e9112c.jpg",
                        )
                    ),
                )
            )
        }
    }
}