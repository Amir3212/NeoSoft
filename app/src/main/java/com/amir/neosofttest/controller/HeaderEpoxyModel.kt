package com.amir.neosofttest.controller

import com.amir.neosofttest.R
import com.amir.neosofttest.databinding.HeaderViewBinding
import com.amir.neosofttest.utils.epoxy.ViewBindingKotlinModel


data class HeaderEpoxyModel(
    val header: String,
) : ViewBindingKotlinModel<HeaderViewBinding>(R.layout.header_view) {
    override fun HeaderViewBinding.bind() {
        textView3.text = header
    }
}