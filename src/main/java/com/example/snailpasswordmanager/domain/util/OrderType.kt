package com.example.snailpasswordmanager.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}