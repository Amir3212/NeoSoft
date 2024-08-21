package com.amir.neosofttest.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amir.neosofttest.model.UserModel
import com.amir.neosofttest.model.UserUI
import com.amir.neosofttest.utils.redux.ApplicationState
import com.amir.neosofttest.utils.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val store: Store<ApplicationState>,
) : ViewModel() {


    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val loading = _loading

    init {
        bindUserList()
    }

    private fun bindUserList() {
        _loading.value = true
        viewModelScope.launch {
            val defaultImage = store.stateFlow.value.defaultImage
            val list: MutableList<UserModel> = ArrayList()
            repeat(25) {
                list.add(
                    UserModel(
                        name = "$it Name", id = it, email = "abc$it@gmail.com", image = defaultImage
                    )
                )
            }
            store.update {
                return@update it.copy(
                    userList = list
                )
            }
            _loading.value = false
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            store.update {
                return@update it.copy(
                    query = query
                )
            }
        }
    }

    fun updateKeyboardOpenStatus(keyBoardOpen: Boolean) {
        viewModelScope.launch {
            store.update {
                return@update it.copy(
                    isKeyBoardOpen = keyBoardOpen
                )
            }
        }
    }

    val homePageData = combine(
        store.stateFlow.map { it.userList },
        store.stateFlow.map { it.query },
        store.stateFlow.map { it.imageList },
        store.stateFlow.map { it.isKeyBoardOpen },
    ) { userList, query, images, isKeyBoardOpen ->
        val list = if (query.isNotEmpty()) {
            userList.filter { it.name.startsWith(query) }
        } else userList


        UserUI(
            user = list, imagesList = images, isKeyBoardOpen = isKeyBoardOpen
        )


    }
}