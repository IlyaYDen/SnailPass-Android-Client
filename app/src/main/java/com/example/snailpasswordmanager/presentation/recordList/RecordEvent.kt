package com.example.snailpasswordmanager.presentation.recordList

import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.util.PasswordOrder

sealed class RecordEvent {
    data class Order(val passwordOrder: PasswordOrder) : RecordEvent()
    data class DeletePassword(val passwordEntity: RecordEntity) : RecordEvent()
    data class AddPassword(val passwordEntity: RecordEntity) : RecordEvent()
    data class UpdateDb(val passwordEntity: RecordEntity) : RecordEvent()
    object GetPasswordsList : RecordEvent()
    object RestorePassword : RecordEvent()
    object ToggleOrderSection : RecordEvent()
}