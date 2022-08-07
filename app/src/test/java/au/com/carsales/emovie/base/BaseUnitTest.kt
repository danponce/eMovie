package au.com.carsales.emovie.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import au.com.carsales.autogate.utils.coroutines.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
abstract class BaseUnitTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @get: Rule
    var testCoroutineRule = TestCoroutineRule()

    /**
     * Initializes the view model that will be tested.
     **/
    abstract fun initViewModel()

    @Before
    open fun setUp() {
        MockitoAnnotations.openMocks(this)
        initViewModel()
    }
}