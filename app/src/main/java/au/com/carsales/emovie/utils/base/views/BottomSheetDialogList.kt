package au.com.carsales.emovie.utils.base.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import au.com.carsales.emovie.R
import au.com.carsales.emovie.databinding.FragmentBottomSheetListBinding
import au.com.carsales.emovie.utils.base.databinding.SingleLayoutBindRecyclerAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Created by Dan on 06, agosto, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class BottomSheetDialogList : BottomSheetDialogFragment() {

    private lateinit var binding : FragmentBottomSheetListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetListBinding.inflate(inflater, container, false)

        // Get args
        val args : BottomSheetDialogListArgs by navArgs()
        val stringList = args.stringList

        setStringRecyclerView(stringList.toList())

        return binding.root
    }

    private fun setStringRecyclerView(stringList : List<String>) {
        binding.listRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = SingleLayoutBindRecyclerAdapter(
                R.layout.view_cell_bottom_sheet_list_item,
                stringList
            ) { _, stringSelected ->



            }
        }
    }
}