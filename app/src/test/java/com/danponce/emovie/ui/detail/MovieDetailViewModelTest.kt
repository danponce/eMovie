package com.danponce.emovie.ui.detail

import com.danponce.emovie.base.BaseUnitTest
import com.danponce.emovie.base.coroutines.TestCoroutineContextProvider
import com.danponce.emovie.domain.model.DomainMovieItem
import com.danponce.emovie.domain.usecase.*
import com.danponce.emovie.factory.MovieDetailFactory
import com.danponce.emovie.ui.mapper.UIMovieDetailMapper
import com.danponce.emovie.ui.mapper.UIMovieItemListMapper
import com.danponce.emovie.ui.mapper.UIMovieItemMapper
import com.danponce.emovie.ui.mapper.UIMovieVideoMapper
import com.danponce.emovie.utils.base.State
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

/**
 * Created by Dan on 07, agosto, 2022
 * Copyright (c) 2022. All rights reserved.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailViewModelTest : BaseUnitTest() {

    lateinit var movieDetailMapper: UIMovieDetailMapper

    @Mock
    lateinit var movieVideoMapper: UIMovieVideoMapper

    @Mock
    lateinit var movieItemListMapper: UIMovieItemListMapper

    @Mock
    lateinit var movieItemMapper: UIMovieItemMapper

    @Mock
    lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

    @Mock
    lateinit var isFavoriteMovieUseCase: GetIsFavoriteMovieUseCase

    @Mock
    lateinit var addFavoriteMovieUseCase: AddFavoriteMovieUseCase

    @Mock
    lateinit var deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase

    lateinit var movieDetailViewModel: MovieDetailViewModel

    override fun initMockCases() {
        movieDetailMapper = UIMovieDetailMapper(movieVideoMapper, movieItemListMapper)

        movieDetailViewModel = MovieDetailViewModel(
            getMovieDetailUseCase,
            isFavoriteMovieUseCase,
            addFavoriteMovieUseCase,
            deleteFavoriteMovieUseCase,
            movieDetailMapper,
            movieItemMapper,
            TestCoroutineContextProvider()
        )
    }

    @Test
    fun `should setStateError when getMovieDetail IsExecuted`() {
        testCoroutineRule.runBlockingTest {
            val exception = State.Error<List<DomainMovieItem>>("Error ")

            mockUseCaseAndExecuteCall(exception)

            TestCase.assertTrue(movieDetailViewModel.movieDetailsLiveData.value == null)
        }
    }

    @Test
    fun `should setStateEmpty when getMovieDetail IsExecuted`() {
        testCoroutineRule.runBlockingTest {
            val exception = State.Empty<List<DomainMovieItem>>()

            mockUseCaseAndExecuteCall(exception)

            TestCase.assertTrue(movieDetailViewModel.movieDetailsLiveData.value == null)
        }
    }

    @Test
    fun `should setStateSuccess when getMovieDetail is executed`() {
        testCoroutineRule.runBlockingTest {

            val movie = MovieDetailFactory.createDomainMovieDetail()
            val successResponse = State.Success(movie)

            mockUseCaseAndExecuteCall(successResponse)

            TestCase.assertNotNull(movieDetailViewModel.movieDetailsLiveData.value)
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
        whenever(getMovieDetailUseCase.getMovieDetail(Mockito.anyString())).thenAnswer {
            flowOf(localResult)
        }

        movieDetailViewModel.getMovieDetails()
    }

}