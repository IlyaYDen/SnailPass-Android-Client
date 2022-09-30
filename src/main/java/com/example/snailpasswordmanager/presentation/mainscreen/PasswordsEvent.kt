package com.example.snailpasswordmanager.presentation.mainscreen

import com.example.snailpasswordmanager.domain.model.PasswordEntity
import com.example.snailpasswordmanager.domain.util.PasswordOrder

sealed class PasswordsEvent {
    data class Order(val passwordOrder: PasswordOrder) : PasswordsEvent()
    data class DeletePassword(val passwordEntity: PasswordEntity) : PasswordsEvent()
    data class AddPassword(val passwordEntity: PasswordEntity) : PasswordsEvent()
    object RestorePassword : PasswordsEvent()
    object ToggleOrderSection : PasswordsEvent()
}