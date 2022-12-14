package com.danponce.emovie.utils.base.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.danponce.emovie.R
import com.danponce.emovie.databinding.FragmentBottomSheetListBinding
import com.danponce.emovie.utils.base.databinding.SingleLayoutBindRecyclerAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Created by Dan on 06, agosto, 2022
 * Copyright (c) 2022. All rights reserved.
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

        setView(stringList.toList(), args.selectionListener)

        return binding.root
    }

    private fun setView(stringList : List<String>, listListener: BottomSheetDialogListListener) {
        binding.closeButton.setOnClickListener {
            dismiss()
        }

        setStringRecyclerView(stringList, listListener)
    }

    private fun setStringRecyclerView(stringList : List<String>, listListener: BottomSheetDialogListListener) {
        binding.listRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = SingleLayoutBindRecyclerAdapter(
                R.layout.view_cell_bottom_sheet_list_item,
                stringList
            ) { _, stringSelected ->
                listListener.onStringSelected(stringSelected)
                dismiss()
            }
        }
    }
}