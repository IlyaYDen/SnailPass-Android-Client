package com.example.snailpasswordmanager.domain.usecase.passwords

import com.example.snailpasswordmanager.domain.model.PasswordEntity
import com.example.snailpasswordmanager.domain.repository.PasswordListRepository
import javax.inject.Inject

class DeletePassword @Inject constructor(
    private val passwordListRepository: PasswordListRepository
     ) {
    suspend operator fun invoke(passwordEntity: PasswordEntity){
        passwordListRepository.deletePassword(passwordEntity)
    }
}