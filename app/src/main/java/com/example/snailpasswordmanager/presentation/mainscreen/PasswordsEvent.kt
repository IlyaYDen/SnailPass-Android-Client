package com.example.snailpasswordmanager.presentation.mainscreen

import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.util.PasswordOrder

sealed class PasswordsEvent {
    data class Order(val passwordOrder: PasswordOrder) : PasswordsEvent()
    data class DeletePassword(val passwordEntity: RecordEntity) : PasswordsEvent()
    data class AddPassword(val passwordEntity: RecordEntity) : PasswordsEvent()
    data class UpdateDb(val passwordEntity: RecordEntity) : PasswordsEvent()
    object GetPasswordsList : PasswordsEvent()
    object RestorePassword : PasswordsEvent()
    object ToggleOrderSection : PasswordsEvent()
}