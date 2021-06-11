package com.cstasenko.mixclouddiscover.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cstasenko.mixclouddiscover.TestUtils
import com.cstasenko.mixclouddiscover.model.MixcloudShow
import com.cstasenko.mixclouddiscover.repository.MixcloudRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserShowsViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var mixcloudRepository: MixcloudRepository

    lateinit var userShowsViewModel: UserShowsViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        userShowsViewModel = UserShowsViewModel(mixcloudRepository)
        Dispatchers.setMain(dispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun userShows_shouldLazyLoadLast10ShowsPlusDummyViews() = runBlockingTest {
        coEvery {
            mixcloudRepository.getLastTenUploadsForUser()
        } returns TestUtils.buildMixcloudUserShowsFlow()

        userShowsViewModel.searchForMostRecentShowsByUser()

        val result = userShowsViewModel.userShows.value

        assertThat(result).isInstanceOf(UserSearchState.OnDataReady::class.java)

        val data = result as UserSearchState.OnDataReady

        // Api only returns 10 results. Asserting that we are adding the dummy views here for a total of 12 results
        assertThat(data.response.size).isEqualTo(12)

        // assert return object is of Domain type Mixcloud show
        assertThat(data.response[0]).isInstanceOf(MixcloudShow::class.java)
    }
}
