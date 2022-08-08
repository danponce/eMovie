package com.danponce.emovie.domain.usecase

import com.danponce.emovie.base.BaseUnitTest
import com.danponce.emovie.data.local.LocalMoviesRepositoryImpl
import com.danponce.emovie.factory.MovieListFactory
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.mockito.Mock

/**
 * Created by Dan on 07, agosto, 2022
 * Copyright (c) 2022. All rights reserved.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class GetFavoriteMoviesUseCaseTest : BaseUnitTest() {
    @Mock
    internal lateinit var mockLocalRepository: LocalMoviesRepositoryImpl

    private lateinit var getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase

    override fun initMockCases() {
        getFavoriteMoviesUseCase =
            GetFavoriteMoviesUseCase(mockLocalRepository)
    }

    @Test
    fun `given local flow result when execute then return a list of movies`() =
        testCoroutineRule.runBlockingTest {
            // given
            val result = MovieListFactory.createDomainMoviesList()

            val localFlowResult = flowOf(result)

            // when
            mockLocalRepositoryAndExecuteCall(localFlowResult)

            // then
            localFlowResult.collect { state ->
                TestCase.assertEquals(result, state)
            }
        }

    @Test
    fun `given local flow result when execute then return an empty movie list`() =
        testCoroutineRule.runBlockingTest {
            // given
            val result = MovieListFactory.emptyMoviesList()

            val localFlowResult = flowOf(result)

            // when
            mockLocalRepositoryAndExecuteCall(localFlowResult)

            // then
            localFlowResult.collect { state ->
                TestCase.assertTrue(state.isEmpty())
            }
        }

    /**
     * Mocks local repository given
     * the flow result and then
     * execute use case call
     *
     * @param localResult       the local result flow to return when mocking the local repository
     */
    private suspend fun <Local> mockLocalRepositoryAndExecuteCall(
        localResult: Flow<Local>
    ) {
        whenever(mockLocalRepository.getFavoriteMovies()).thenAnswer {
            localResult
        }

        getFavoriteMoviesUseCase.getFavoriteMovies()
    }
}