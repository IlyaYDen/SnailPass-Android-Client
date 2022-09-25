package com.example.snailpasswordmanager.domain.usecase.passwords

data class PasswordUseCases(
    val getPasswordList: GetPasswordList,
    val deletePassword: DeletePassword,
    val insertPassword: InsertPassword
)