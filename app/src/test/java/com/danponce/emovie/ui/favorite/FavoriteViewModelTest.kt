package com.danponce.emovie.ui.favorite

import com.danponce.emovie.base.BaseUnitTest
import com.danponce.emovie.base.coroutines.TestCoroutineContextProvider
import com.danponce.emovie.domain.model.DomainMovieItem
import com.danponce.emovie.domain.usecase.DeleteFavoriteMovieUseCase
import com.danponce.emovie.domain.usecase.GetFavoriteMoviesUseCase
import com.danponce.emovie.ui.favorites.FavoritesViewModel
import com.danponce.emovie.ui.mapper.UIMovieItemListMapper
import com.danponce.emovie.ui.mapper.UIMovieItemMapper
import com.danponce.emovie.utils.base.State
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.mockito.Mock

/**
 * Created by Dan on 07, agosto, 2022
 * Copyright (c) 2022. All rights reserved.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteViewModelTest : BaseUnitTest() {

    @Mock
    lateinit var movieItemListMapper: UIMovieItemListMapper

    @Mock
    lateinit var movieItemMapper: UIMovieItemMapper

    @Mock
    lateinit var getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase

    @Mock
    lateinit var deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase

    lateinit var favoritesViewModel: FavoritesViewModel

    override fun initMockCases() {
        favoritesViewModel = FavoritesViewModel(
            getFavoriteMoviesUseCase,
            deleteFavoriteMovieUseCase,
            movieItemListMapper,
            movieItemMapper,
            TestCoroutineContextProvider()
        )
    }

    @Test
    fun `should setStateError when getUpcomingMovies IsExecuted`() {
        testCoroutineRule.runBlockingTest {
            val exception = State.Error<List<DomainMovieItem>>("Error ")

            mockUseCaseAndExecuteCall(exception)

            TestCase.assertTrue(favoritesViewModel.favoritesLiveData.value == null)
        }
    }

    @Test
    fun `should setStateEmpty when getUpcomingMovies IsExecuted`() {
        testCoroutineRule.runBlockingTest {
            val exception = State.Empty<List<DomainMovieItem>>()

            mockUseCaseAndExecuteCall(exception)

            TestCase.assertTrue(favoritesViewModel.favoritesLiveData.value == null)
        }
    }

    @Test
    fun `should setStateSuccess when getFavoriteMovies is executed`() {
        testCoroutineRule.runBlockingTest {

            val successResponse = State.Success(listOf<DomainMovieItem>())

            mockUseCaseAndExecuteCall(successResponse)

            TestCase.assertNotNull(favoritesViewModel.favoritesLiveData.value)
        }
    }

    /**
     * Mocks a use case given
     * the flow result and then
     * execute view model call
     *
     * @param localResult       the local result flow to return when mocking the local repository
     */
    private suspend fun <Local> mockUseCaseAndExecuteCall(
        localResult: State<Local>
    ) {
        whenever(getFavoriteMoviesUseCase.getFavoriteMovies()).thenAnswer {
            flowOf(localResult)
        }

        favoritesViewModel.getFavoritesMovies()
    }

}