package au.com.carsales.emovie.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import au.com.carsales.autogate.utils.coroutines.TestCoroutineRule
import au.com.carsales.emovie.data.local.LocalMoviesRepositoryImpl
import au.com.carsales.emovie.data.remote.RemoteMoviesRepositoryImpl
import au.com.carsales.emovie.data.remote.state.APIState
import au.com.carsales.emovie.factory.MovieDetailFactory
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMovieDetailUseCaseTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    internal lateinit var mockRemoteRepository: RemoteMoviesRepositoryImpl

    @Mock
    internal lateinit var mockLocalRepository: LocalMoviesRepositoryImpl

    private lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
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
            whenever(mockLocalRepository.getMovie(movieId)).thenAnswer {
                localFlowResult
            }
            whenever(mockRemoteRepository.getMovieDetail(movieId)).thenAnswer {
                flowResult
            }
            getMovieDetailUseCase.getMovieDetail(movieId)

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
            whenever(mockLocalRepository.getMovie(movieId)).thenAnswer {
                localFlowResult
            }
            whenever(mockRemoteRepository.getMovieDetail(movieId)).thenAnswer {
                flowResult
            }
            getMovieDetailUseCase.getMovieDetail(movieId)

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
            whenever(mockLocalRepository.getMovie(movieId)).thenAnswer {
                localFlowResult
            }

            whenever(mockRemoteRepository.getMovieDetail(movieId)).thenAnswer {
                flowResult
            }
            getMovieDetailUseCase.getMovieDetail(movieId)

            // then
            assertNotNull(flowResult)
            flowResult.collect { state ->
                assertEquals(empty, state)
            }
        }
}