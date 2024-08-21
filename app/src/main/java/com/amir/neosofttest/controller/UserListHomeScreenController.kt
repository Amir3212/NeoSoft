package com.amir.neosofttest.controller

import android.widget.Toast
import com.airbnb.epoxy.TypedEpoxyController
import com.amir.neosofttest.model.UserUI
import com.amir.neosofttest.utils.App
import com.amir.neosofttest.viewModel.MainActivityViewModel

class UserListHomeScreenController(
    private val viewModel: MainActivityViewModel,
) : TypedEpoxyController<UserUI>() {
    override fun buildModels(userData: UserUI?) {

        if (userData == null) return


        ImageCarousilEpoxyModel(viewModel, userData.imagesList)
            .id("ImageCarousil")
            .addIf(!userData.isKeyBoardOpen, this)


        SearchEpoxyModel(viewModel) {
            viewModel.search(it)
        }.id("SearchView").addTo(this)


        userData.user.forEach {
            ProductByCategoryEpoxyModel(it) { user ->
                Toast.makeText(App.instance, "Clicked On " + user.name, Toast.LENGTH_SHORT).show()
            }.id(it.id).addTo(this)
        }
    }

}