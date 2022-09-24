package com.example.snailpasswordmanager.domain.repository

import com.example.snailpasswordmanager.domain.model.PasswordEntity

interface PasswordListRepository {
    fun addPassword(passwordEntity: PasswordEntity)
    fun removePassword(passwordEntity: PasswordEntity)
    fun getPasswordList():List<PasswordEntity>
    fun getPassword(passwordId: Int):PasswordEntity?
}