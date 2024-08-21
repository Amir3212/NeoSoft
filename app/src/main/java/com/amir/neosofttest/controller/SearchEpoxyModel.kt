package com.amir.neosofttest.controller

import androidx.core.widget.addTextChangedListener
import com.amir.neosofttest.R
import com.amir.neosofttest.databinding.SearchViewBinding
import com.amir.neosofttest.utils.epoxy.ViewBindingKotlinModel
import com.amir.neosofttest.viewModel.MainActivityViewModel


data class SearchEpoxyModel(
    val viewModel: MainActivityViewModel,
    val onSearchQueryChange: (String) -> Unit,
) : ViewBindingKotlinModel<SearchViewBinding>(R.layout.search_view) {
    override fun SearchViewBinding.bind() {
        etSearch.addTextChangedListener { query ->
            query?.apply {
                onSearchQueryChange.invoke(this.toString())
            }
        }

        etSearch.setOnFocusChangeListener { _, hasFocus ->
            viewModel.updateKeyboardOpenStatus(hasFocus)
        }

    }
}