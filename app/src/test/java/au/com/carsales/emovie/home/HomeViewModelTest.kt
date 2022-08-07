package au.com.carsales.emovie.home

import au.com.carsales.emovie.base.BaseUnitTest
import au.com.carsales.emovie.base.coroutines.TestCoroutineContextProvider
import au.com.carsales.emovie.domain.model.DomainMovieItem
import au.com.carsales.emovie.ui.home.HomeViewModel
import au.com.carsales.emovie.domain.usecase.GetTopRatedMoviesUseCase
import au.com.carsales.emovie.domain.usecase.GetUpcomingMoviesUseCase
import au.com.carsales.emovie.ui.mapper.UIMovieItemListMapper
import au.com.carsales.emovie.ui.model.UIMovieItem
import au.com.carsales.emovie.utils.base.State
import au.com.carsales.emovie.utils.datastore.UserPreferencesRepository
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

    override fun initDataMocks() {
        MockitoAnnotations.openMocks(this)
    }

    override fun initViewModel() {
        homeViewModel = HomeViewModel(
            getUpcomingMoviesUseCase,
            getTopRatedMoviesUseCase,
            movieItemListMapper,
            userPreferencesRepository,
            TestCoroutineContextProvider()
        )
    }

    @Test
    fun should_setStateError_when_getUpcomingMovies_IsExecuted() {
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
    fun should_setStateEmpty_when_getUpcomingMovies_IsExecuted() {
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
    fun should_setViewStateSuccess_when_getUpcomingMovies_is_executed() {
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