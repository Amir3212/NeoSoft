package com.amir.neosofttest.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.amir.neosofttest.controller.UserListHomeScreenController
import com.amir.neosofttest.databinding.ActivityMainBinding
import com.amir.neosofttest.utils.flowCollector.collectFlow
import com.amir.neosofttest.utils.redux.ApplicationState
import com.amir.neosofttest.utils.redux.Store
import com.amir.neosofttest.viewModel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var controller: UserListHomeScreenController

    @Inject
    lateinit var store: Store<ApplicationState>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        controller = UserListHomeScreenController(viewModel)


        binding.categoryListingController.setController(controller)

        collectFlow(viewModel.homePageData) {
            Log.d("TAG", "homePageData: ${it.isKeyBoardOpen}")
            controller.setData(it)
        }
        collectFlow(viewModel.loading) {
            binding.progressBar.isVisible = it
        }


        binding.apply {
            floatingActionButton.setOnClickListener {
                setUpBottomDialogSheet()
            }
        }
    }


    private fun setUpBottomDialogSheet() {
        val fragment = StaticsFragment()
        supportFragmentManager.let { fragment.show(it, StaticsFragment.TAG) }
    }


}