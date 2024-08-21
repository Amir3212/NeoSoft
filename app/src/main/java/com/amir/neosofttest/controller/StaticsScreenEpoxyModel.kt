package com.amir.neosofttest.controller

import com.amir.neosofttest.R
import com.amir.neosofttest.databinding.BottomDialogListViewBinding
import com.amir.neosofttest.utils.epoxy.ViewBindingKotlinModel


data class StaticsScreenEpoxyModel(
    val title: String,
) : ViewBindingKotlinModel<BottomDialogListViewBinding>(R.layout.bottom_dialog_list_view) {
    override fun BottomDialogListViewBinding.bind() {
        textView3.text = title
    }
}