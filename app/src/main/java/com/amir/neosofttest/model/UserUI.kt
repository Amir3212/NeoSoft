package com.amir.neosofttest.model

data class UserUI(
    val user: List<UserModel>,
    val imagesList: List<String> = emptyList(),
    val isKeyBoardOpen: Boolean = false,
)
