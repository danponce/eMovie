package au.com.carsales.emovie.utils.base.views

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Dan on 06, agosto, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
interface BottomSheetDialogListListener : Parcelable {
    fun onStringSelected(string: String)
    override fun describeContents(): Int = 0
    override fun writeToParcel(p0: Parcel?, p1: Int) {}
}