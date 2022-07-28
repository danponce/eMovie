package au.com.carsales.emovie.utils.base.coroutines

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by Dan on 10, February, 2020
 * Copyright (c) 2020 Carsales. All rights reserved.
 *
 * Used to change or mock
 * coroutines context in tests
 */
open class CoroutinesContextProvider @Inject constructor() {
    open val Main : CoroutineContext by lazy { Dispatchers.Main }
    open val IO : CoroutineContext by lazy { Dispatchers.IO }
}