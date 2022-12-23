package com.example.snailpasswordmanager.presentation.mainscreen

import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.util.OrderType
import com.example.snailpasswordmanager.domain.util.PasswordOrder

data class PasswordsState(
    val passwords: List<RecordEntity> = emptyList(),
    val passwordOrder: PasswordOrder = PasswordOrder.Date(OrderType.Ascending),
    val isOrderSessionVisible: Boolean = false
)
