package au.com.carsales.emovie.home

import androidx.lifecycle.Observer
import au.com.carsales.emovie.base.BaseUnitTest
import au.com.carsales.emovie.base.coroutines.TestCoroutineContextProvider
import au.com.carsales.emovie.data.remote.model.TVSeriesData
import au.com.carsales.emovie.domain.GetSeriesSearchUseCase
import au.com.carsales.emovie.domain.GetShowsUseCase
import au.com.carsales.emovie.ui.home.HomeViewModel
import au.com.carsales.emovie.ui.mapper.TVSeriesShowMapper
import au.com.carsales.emovie.data.local.model.EntityMovieItem
import au.com.carsales.emovie.utils.base.State
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@ExperimentalCoroutinesApi
class HomeViewModelTest : BaseUnitTest() {

    @Mock
    lateinit var tvSeriesShowMapper: TVSeriesShowMapper

    @Mock
    lateinit var getShowsUseCase: GetShowsUseCase

    @Mock
    lateinit var getSeriesSearchUseCase: GetSeriesSearchUseCase

    @Mock
    lateinit var searchObserver : Observer<State<List<EntityMovieItem>>>

    lateinit var homeViewModel: HomeViewModel

    override fun initDataMocks() {
        MockitoAnnotations.openMocks(this)
    }

    override fun initViewModel() {
        homeViewModel = HomeViewModel(
            tvSeriesShowMapper,
            getShowsUseCase,
            getSeriesSearchUseCase,
            TestCoroutineContextProvider()
        ).apply {
            tvShowSearchLiveData.observeForever(searchObserver)
        }
    }

    @Test(expected = Exception::class)
    fun should_setStateError_when_searchingIsExecuted() {
        testCoroutineRule.runBlockingTest {
            val exception = Exception("Error ")

            whenever(
                getSeriesSearchUseCase.getTVSeriesSearch(Mockito.anyString())
            ).thenThrow(exception)

            homeViewModel.searchTVShows("")

            Mockito.verify(searchObserver).onChanged(Mockito.refEq(State.error()))
        }
    }

    @Test
    fun should_setViewStateSuccess_when_updateSourcingIsExecuted() {
        testCoroutineRule.runBlockingTest {

            val responseData = listOf(TVSeriesData())

            whenever(
                getSeriesSearchUseCase.getTVSeriesSearch(Mockito.anyString())
            ).doReturn(responseData)

            homeViewModel.searchTVShows("")

//            Mockito.verify(searchObserver).onChanged(Mockito.refEq(State.Success()))
            assert(homeViewModel.tvShowSearchLiveData.value is State.Success)
        }
    }


}