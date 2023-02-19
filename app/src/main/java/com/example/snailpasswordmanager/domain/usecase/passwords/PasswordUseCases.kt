package com.example.snailpasswordmanager.domain.usecase.passwords

import javax.inject.Inject

data class PasswordUseCases @Inject constructor(
    val getPasswordList: GetPasswordList,
    val deletePassword: DeletePassword,
    val insertPassword: InsertPassword,
    val editPassword: EditPassword
)