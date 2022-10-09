package com.example.snailpasswordmanager.domain.usecase.user

import com.example.snailpasswordmanager.domain.crypto.AES.AESUtil
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.UserRepository

class UserRegisterUseCase (
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userEntity: UserEntity){
        //userRepository.getUser()
        val a = AESUtil.encryptTimes(userEntity.password,200_000)

    }
}