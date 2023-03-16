package com.example.snailpasswordmanager.services

class ConnectionCheck {
    fun invoke(): Boolean {
        return try {
            val command = "ping -c 1 google.com"
            Runtime.getRuntime().exec(command).waitFor() == 0
        } catch (e: Exception) {
            false
        }
    }
}