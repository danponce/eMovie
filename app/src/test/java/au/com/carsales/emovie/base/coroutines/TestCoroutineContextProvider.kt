package com.danponce.emovie.base.coroutines

import com.danponce.emovie.utils.base.coroutines.CoroutinesContextProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * Created by Dan on 25, June, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 *
 * Changes the coroutine context
 * provider so the Dispatchers are
 * Unconfined. This way coroutines
 * are not confined to any specific
 * thread
 */
class TestCoroutineContextProvider : CoroutinesContextProvider() {
    override val Main: CoroutineContext by lazy { Dispatchers.Unconfined }
    override val IO: CoroutineContext by lazy { Dispatchers.Unconfined }
}