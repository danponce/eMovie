package com.danponce.emovie.domain.usecase

import com.danponce.emovie.base.BaseUnitTest
import com.danponce.emovie.data.local.LocalMoviesRepositoryImpl
import com.danponce.emovie.data.remote.state.APIState
import com.danponce.emovie.factory.MovieListFactory
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.TestCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.mockito.Mock

/**
 * Created by Dan on 07, agosto, 2022
 * Copyright (c) 2022. All rights reserved.
 */
class GetIsFavoriteMovieUseCaseTest : BaseUnitTest() {

    @Mock
    internal lateinit var mockLocalRepository: LocalMoviesRepositoryImpl

    private lateinit var getIsFavoriteMovieUseCase: GetIsFavoriteMovieUseCase

    override fun initMockCases() {
        getIsFavoriteMovieUseCase =
            GetIsFavoriteMovieUseCase(mockLocalRepository)
    }

    @Test
    fun `given local flow result when execute then return the movie is a favorite one`() =
        testCoroutineRule.runBlockingTest {
            // given
            val movieId = ""
            val result = true

            val localFlowResult = flowOf(result)

            // when
            mockLocalRepositoryAndExecuteCall(movieId, localFlowResult)

            // then
            localFlowResult.collect { state ->
                TestCase.assertEquals(result, state)
            }
        }

    @Test
    fun `given local flow result when execute then return the movie is NOT a favorite one`() =
        testCoroutineRule.runBlockingTest {
            // given
            val movieId = ""
            val result = false

            val localFlowResult = flowOf(result)

            // when
            mockLocalRepositoryAndExecuteCall(movieId, localFlowResult)

            // then
            localFlowResult.collect { state ->
                TestCase.assertEquals(result, state)
            }
        }

    /**
     * Mocks local repository given
     * the flow result and then
     * execute use case call
     *
     * @param movieId           search with this movie id if is favorite
     * @param localResult       the local result flow to return when mocking the local repository
     */
    private suspend fun <Local> mockLocalRepositoryAndExecuteCall(
        movieId: String,
        localResult: Flow<Local>
    ) {
        whenever(mockLocalRepository.isFavorite(movieId)).thenAnswer {
            localResult
        }

        getIsFavoriteMovieUseCase.isMovieFavorite(movieId)
    }
}