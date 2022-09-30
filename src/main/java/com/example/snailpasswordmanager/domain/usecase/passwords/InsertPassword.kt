package com.example.snailpasswordmanager.domain.usecase.passwords

import android.util.Log
import com.example.snailpasswordmanager.domain.model.InvalidPasswordException
import com.example.snailpasswordmanager.domain.model.PasswordEntity
import com.example.snailpasswordmanager.domain.repository.PasswordListRepository
import javax.inject.Inject

class InsertPassword @Inject constructor(
    private val passwordListRepository: PasswordListRepository
    ) {

    @Throws(InvalidPasswordException::class)
    suspend operator fun invoke(passwordEntity: PasswordEntity){
        Log.d("testb","2")
        if(passwordEntity.service.isBlank())
            throw InvalidPasswordException("The service can't be empty.")
        if(passwordEntity.login.isBlank())
            throw InvalidPasswordException("The login can't be empty.")
        if(passwordEntity.password.isBlank())
            throw InvalidPasswordException("The password can't be empty.")
        passwordListRepository.insertPassword(passwordEntity)
    }
}
