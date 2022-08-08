package com.danponce.emovie.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.danponce.autogate.utils.coroutines.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
@ExperimentalCoroutinesApi
abstract class BaseUnitTest {
    @get:Rule
    var instantTaskExecutorRule : TestRule = InstantTaskExecutorRule()


    @get: Rule
    var testCoroutineRule = TestCoroutineRule()

    /**
     * Initializes the view model that will be tested.
     **/
    abstract fun initMockCases()

    @Before
    open fun setUp() {
        MockitoAnnotations.openMocks(this)
        initMockCases()
    }
}