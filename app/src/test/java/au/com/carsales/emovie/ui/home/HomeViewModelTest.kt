package com.danponce.emovie.ui.home

import com.danponce.emovie.base.BaseUnitTest
import com.danponce.emovie.base.coroutines.TestCoroutineContextProvider
import com.danponce.emovie.domain.model.DomainMovieItem
import com.danponce.emovie.domain.usecase.GetTopRatedMoviesUseCase
import com.danponce.emovie.domain.usecase.GetUpcomingMoviesUseCase
import com.danponce.emovie.ui.mapper.UIMovieItemListMapper
import com.danponce.emovie.utils.base.State
import com.danponce.emovie.utils.datastore.UserPreferencesRepository
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.TestCase.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@ExperimentalCoroutinesApi
class HomeViewModelTest : BaseUnitTest() {

    @Mock
    lateinit var movieItemListMapper: UIMovieItemListMapper

    @Mock
    lateinit var getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase

    @Mock
    lateinit var getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase

    @Mock
    lateinit var userPreferencesRepository: UserPreferencesRepository

    lateinit var homeViewModel: HomeViewModel

    override fun initMockCases() {
        homeViewModel = HomeViewModel(
            getUpcomingMoviesUseCase,
            getTopRatedMoviesUseCase,
            movieItemListMapper,
            userPreferencesRepository,
            TestCoroutineContextProvider()
        )
    }

    @Test
    fun `should setStateError when getUpcomingMovies IsExecuted`() {
        testCoroutineRule.runBlockingTest {
            val exception = State.Error<List<DomainMovieItem>>("Error ")

            whenever(
                getUpcomingMoviesUseCase.getUpcomingMovies()
            ).thenAnswer{ flowOf(exception) }

            homeViewModel.getUpcomingMovies()

            assertTrue(homeViewModel.upcomingMoviesLiveData.value == null)
        }
    }

    @Test
    fun `should setStateEmpty when getUpcomingMovies IsExecuted`() {
        testCoroutineRule.runBlockingTest {
            val exception = State.Empty<List<DomainMovieItem>>()

            whenever(
                getUpcomingMoviesUseCase.getUpcomingMovies()
            ).thenAnswer{ flowOf(exception) }

            homeViewModel.getUpcomingMovies()

            assertTrue(homeViewModel.upcomingMoviesLiveData.value == null)
        }
    }

    @Test
    fun `should setViewStateSuccess when getUpcomingMovies is executed`() {
        testCoroutineRule.runBlockingTest {

            val successResponse = State.Success(listOf<DomainMovieItem>())

            whenever(
                getUpcomingMoviesUseCase.getUpcomingMovies()
            ).thenAnswer { flowOf(successResponse) }

            homeViewModel.getUpcomingMovies()

            assertNotNull(homeViewModel.upcomingMoviesLiveData.value)
        }
    }

}