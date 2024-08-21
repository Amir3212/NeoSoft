package com.amir.neosofttest.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amir.neosofttest.controller.StaticsScreenScreenController
import com.amir.neosofttest.databinding.FragmentStatsticsBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StaticsFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentStatsticsBinding
    private lateinit var controller: StaticsScreenScreenController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentStatsticsBinding.inflate(inflater, container, false)
        controller = StaticsScreenScreenController()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            bottomDialogController.setController(controller)
        }
        controller.setData(getLocalList())
    }

    private fun getLocalList(): List<String> {
        val list: MutableList<String> = ArrayList()
        list.add("Apple")
        list.add("Mango")
        list.add("Banana")
        list.add("Pine-Apple")
        list.add("Grapes")
        list.add("Orange")
        list.add("Cherry")
        list.add("Mango")
        list.add("blueberry")
        return list
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog?.setOnShowListener { it ->
            val d = it as BottomSheetDialog
            val bottomSheet =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }

    companion object {
        const val TAG = "ModalBottomSheetDialog"
    }

}