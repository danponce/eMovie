package com.danponce.emovie.domain.usecase

import com.danponce.emovie.base.BaseUnitTest
import com.danponce.emovie.data.local.LocalMoviesRepositoryImpl
import com.danponce.emovie.data.remote.RemoteMoviesRepositoryImpl
import com.danponce.emovie.data.remote.state.APIState
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
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class GetUpcomingMoviesUseCaseTest : BaseUnitTest() {

    @Mock
    internal lateinit var mockRemoteRepository: RemoteMoviesRepositoryImpl

    @Mock
    internal lateinit var mockLocalRepository: LocalMoviesRepositoryImpl

    private lateinit var getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase

    override fun initMockCases() {
        getUpcomingMoviesUseCase =
            GetUpcomingMoviesUseCase(mockLocalRepository, mockRemoteRepository)
    }

    @Test
    fun `given flow results when execute then return Success API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val result = MovieListFactory.createDomainMoviesList()
            val success = APIState.Success(result)
            val flowResult = flowOf(success)

            val localFlowResult = flowOf(result)

            // when
            mockRepositoriesAndExecuteCall(localFlowResult, flowResult)

            // then
            TestCase.assertNotNull(flowResult)
            flowResult.collect { state ->
                TestCase.assertEquals(success, state)
            }
        }

    @Test
    fun `given flow results when execute then return Error API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val error = APIState.Error("Error")
            val flowResult = flowOf(error)

            val localFlowResult = flowOf(null)

            // when
            mockRepositoriesAndExecuteCall(localFlowResult, flowResult)

            // then
            TestCase.assertNotNull(flowResult)
            flowResult.collect { state ->
                TestCase.assertEquals(error, state)
            }
        }

    @Test
    fun `given flow results when execute then return Empty API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val empty = APIState.Empty("Empty")
            val flowResult = flowOf(empty)

            val localFlowResult = flowOf(null)

            // when
            mockRepositoriesAndExecuteCall(localFlowResult, flowResult)

            // then
            TestCase.assertNotNull(flowResult)
            flowResult.collect { state ->
                TestCase.assertEquals(empty, state)
            }
        }

    /**
     * Mocks local and remote repositories
     * given some flow results and then
     * execute use case call
     *
     * @param localResult       the local result flow to return when mocking the local repository
     * @param remoteResult      the remote result flow to return when mocking the remote repository
     */
    private suspend fun <Local, Remote> mockRepositoriesAndExecuteCall(
        localResult: Flow<Local>,
        remoteResult: Flow<Remote>
    ) {
        whenever(mockLocalRepository.getUpcomingMovies()).thenAnswer {
            localResult
        }
        whenever(mockRemoteRepository.getUpcomingMovies()).thenAnswer {
            remoteResult
        }
        getUpcomingMoviesUseCase.getUpcomingMovies()
    }
}