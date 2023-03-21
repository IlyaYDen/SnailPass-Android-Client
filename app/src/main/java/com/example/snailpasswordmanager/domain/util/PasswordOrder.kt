package com.example.snailpasswordmanager.domain.util

sealed class PasswordOrder(val orderType: OrderType) {
    class Service(orderType: OrderType): PasswordOrder(orderType)
    class Date(orderType: OrderType): PasswordOrder(orderType)
}