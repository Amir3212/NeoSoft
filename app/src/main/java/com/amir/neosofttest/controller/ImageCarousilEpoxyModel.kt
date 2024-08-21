package com.amir.neosofttest.controller

import com.amir.neosofttest.R
import com.amir.neosofttest.adapter.ImageSliderAdapter
import com.amir.neosofttest.databinding.ViewPagerViewBinding
import com.amir.neosofttest.listeners.ViewPagerPageSelectListener
import com.amir.neosofttest.utils.epoxy.ViewBindingKotlinModel
import com.amir.neosofttest.viewModel.MainActivityViewModel
import com.google.android.material.tabs.TabLayoutMediator


data class ImageCarousilEpoxyModel(
    val viewModel: MainActivityViewModel,
    val imageUrls: List<String>,
) : ViewBindingKotlinModel<ViewPagerViewBinding>(R.layout.view_pager_view) {
    override fun ViewPagerViewBinding.bind() {

        val adapter = ImageSliderAdapter(imageUrls)
        viewPager2.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager2) { _, _ -> }.attach()

        viewPager2.registerOnPageChangeCallback(ViewPagerPageSelectListener(viewModel))
    }
}