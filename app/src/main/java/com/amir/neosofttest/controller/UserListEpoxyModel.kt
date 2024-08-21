package com.amir.neosofttest.controller

import com.amir.neosofttest.R
import com.amir.neosofttest.databinding.UserListViewBinding
import com.amir.neosofttest.model.UserModel
import com.amir.neosofttest.utils.epoxy.ViewBindingKotlinModel


data class ProductByCategoryEpoxyModel(
    val userData: UserModel,
    val userClickListener: (UserModel) -> Unit,
) : ViewBindingKotlinModel<UserListViewBinding>(R.layout.user_list_view) {
    override fun UserListViewBinding.bind() {
        user = userData

        mainView.setOnClickListener {
            userClickListener.invoke(userData)
        }
    }
}