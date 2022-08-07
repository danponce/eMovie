package au.com.carsales.emovie.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import au.com.carsales.autogate.utils.coroutines.TestCoroutineRule
import au.com.carsales.emovie.MyApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
abstract class BaseUnitTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @get: Rule
    var testCoroutineRule = TestCoroutineRule()

    /**
     * Mock the necessary variables that will be used to testing.
     **/
    abstract fun initDataMocks()

    /**
     * Initializes the view model that will be tested.
     **/
    abstract fun initViewModel()

    @Before
    open fun setUp() {
        initDataMocks()
        initViewModel()
    }
}