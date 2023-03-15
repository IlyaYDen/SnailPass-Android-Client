package com.example.snailpasswordmanager.data.repository

data class ServerResponse<T> (
    private val content: T,
    private val statusCode: Int
        )