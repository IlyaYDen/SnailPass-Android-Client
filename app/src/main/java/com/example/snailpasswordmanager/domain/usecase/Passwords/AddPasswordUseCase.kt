package com.example.snailpasswordmanager.domain.usecase.Passwords

import com.example.snailpasswordmanager.domain.model.PasswordEntity
import com.example.snailpasswordmanager.domain.repository.PasswordListRepository

class AddPasswordUseCase(private val passwordListRepository: PasswordListRepository) {

    fun execute(passwordEntity: PasswordEntity){
        passwordListRepository.addPassword(passwordEntity)
    }
}