package com.danponce.emovie.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.danponce.autogate.utils.coroutines.TestCoroutineRule
import com.danponce.emovie.data.remote.RemoteMoviesRepositoryImpl
import com.danponce.emovie.data.remote.RemoteMoviesService
import com.danponce.emovie.data.remote.mapper.RemoteToDomainMovieDetailMapper
import com.danponce.emovie.data.remote.mapper.RemoteToDomainMovieMapper
import com.danponce.emovie.data.remote.state.APIState
import com.danponce.emovie.domain.model.DomainMovieItem
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner.Silent::class)
class RemoteMoviesRepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    internal lateinit var mockService: RemoteMoviesService

    @Mock
    internal lateinit var mockToDomainMapper: RemoteToDomainMovieMapper

    @Mock
    internal lateinit var mockMovieDetailMapper: RemoteToDomainMovieDetailMapper

    private lateinit var repositoryImpl: RemoteMoviesRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repositoryImpl = RemoteMoviesRepositoryImpl(mockService, mockToDomainMapper, mockMovieDetailMapper)
    }

    @Test
    fun `given RemoteMoviesService when call getUpcomingMovies then return Success API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val success = APIState.Success(listOf<DomainMovieItem>())
            val flowResult = flowOf(success)

            // when
            whenever(mockService.getUpcomingMovies()).thenAnswer {
                flowResult
            }

            repositoryImpl.getUpcomingMovies()

            // then
            assertNotNull(flowResult)
            flowResult.collect { state ->
                assertEquals(success, state)
            }
        }

    @Test
    fun `given RemoteMoviesService when call getTopRated then return Error API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val errorMessage = "Error"
            val error = APIState.Error(errorMessage)
            val flowResult = flowOf(error)

            // when
            whenever(mockService.getTopRated()).thenAnswer {
                flowResult
            }
            repositoryImpl.getTopRatedMovies()

            // then
            assertNotNull(flowResult)
            flowResult.collect { state ->
                assertEquals(error, state)
                TestCase.assertEquals(error.error, errorMessage)
            }
        }

    @Test
    fun `given RemoteMoviesService when call getTopRated then return Empty API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val emptyMessage = "Empty"
            val emptyState = APIState.Empty(emptyMessage)
            val flowResult = flowOf(emptyState)

            // when
            whenever(mockService.getTopRated()).thenAnswer {
                flowResult
            }
            repositoryImpl.getTopRatedMovies()

            // then
            assertNotNull(flowResult)
            flowResult.collect { state ->
                assertEquals(emptyState, state)
                TestCase.assertEquals(emptyState.error, emptyMessage)
            }
        }
}