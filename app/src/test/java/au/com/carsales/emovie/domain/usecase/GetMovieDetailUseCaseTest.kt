package com.danponce.emovie.domain.usecase

import com.danponce.emovie.base.BaseUnitTest
import com.danponce.emovie.data.local.LocalMoviesRepositoryImpl
import com.danponce.emovie.data.remote.RemoteMoviesRepositoryImpl
import com.danponce.emovie.data.remote.state.APIState
import com.danponce.emovie.factory.MovieDetailFactory
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

@OptIn(ExperimentalCoroutinesApi::class)
class GetMovieDetailUseCaseTest : BaseUnitTest() {

    @Mock
    internal lateinit var mockRemoteRepository: RemoteMoviesRepositoryImpl

    @Mock
    internal lateinit var mockLocalRepository: LocalMoviesRepositoryImpl

    private lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

    @Before
    override fun initMockCases() {
        getMovieDetailUseCase = GetMovieDetailUseCase(mockLocalRepository, mockRemoteRepository)
    }

    @Test
    fun `given movieId true when execute then return Success API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val movieId = ""
            val result = MovieDetailFactory.createDomainMovieDetail()
            val success = APIState.Success(result)
            val flowResult = flowOf(success)

            val localFlowResult = flowOf(result)

            // when
            mockRepositoriesAndExecuteCall(movieId, localFlowResult, flowResult)

            // then
            assertNotNull(flowResult)
            flowResult.collect { state ->
                assertEquals(success, state)
            }
        }

    @Test
    fun `given movieId true when execute then return Error API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val movieId = ""
            val error = APIState.Error("Error")
            val flowResult = flowOf(error)

            val localFlowResult = flowOf(null)

            // when
            mockRepositoriesAndExecuteCall(movieId, localFlowResult, flowResult)

            // then
            assertNotNull(flowResult)
            flowResult.collect { state ->
                assertEquals(error, state)
            }
        }

    @Test
    fun `given movieId true when execute then return Empty API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val movieId = ""
            val empty = APIState.Empty("Empty")
            val flowResult = flowOf(empty)

            val localFlowResult = flowOf(null)

            // when
            mockRepositoriesAndExecuteCall(movieId, localFlowResult, flowResult)

            // then
            assertNotNull(flowResult)
            flowResult.collect { state ->
                assertEquals(empty, state)
            }
        }

    /**
     * Mocks local and remote repositories
     * given some flow results and then
     * execute use case call
     *
     * @param movieId           the movie id to make the repo and use case calls
     * @param localResult       the local result flow to return when mocking the local repository
     * @param remoteResult      the remote result flow to return when mocking the remote repository
     */
    private suspend fun <Local, Remote> mockRepositoriesAndExecuteCall(
        movieId: String,
        localResult: Flow<Local>,
        remoteResult: Flow<Remote>
    ) {
        whenever(mockLocalRepository.getMovie(movieId)).thenAnswer {
            localResult
        }
        whenever(mockRemoteRepository.getMovieDetail(movieId)).thenAnswer {
            remoteResult
        }
        getMovieDetailUseCase.getMovieDetail(movieId)
    }
}