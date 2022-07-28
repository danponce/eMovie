package au.com.carsales.emovie.ui.detail.episode

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class EpisodesPagerAdapter(
    fragment: Fragment,
    private val fragmentList : List<Fragment>
) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position]
}