package com.example.snailpasswordmanager.presentation.mainscreen

import com.example.snailpasswordmanager.domain.model.PasswordEntity
import com.example.snailpasswordmanager.domain.util.OrderType
import com.example.snailpasswordmanager.domain.util.PasswordOrder

data class PasswordsState(
    val passwords: List<PasswordEntity> = emptyList(),
    val passwordOrder: PasswordOrder = PasswordOrder.Date(OrderType.Ascending),
    val isOrderSessionVisible: Boolean = false
)
