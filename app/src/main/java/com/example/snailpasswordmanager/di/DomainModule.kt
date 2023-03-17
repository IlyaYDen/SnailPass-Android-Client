package com.example.snailpasswordmanager.di

import com.example.snailpasswordmanager.data.repository.AuthorizationData
import com.example.snailpasswordmanager.domain.repository.RecordListRepository
import com.example.snailpasswordmanager.domain.repository.UserRepository
import com.example.snailpasswordmanager.domain.usecase.passwords.*
import com.example.snailpasswordmanager.domain.usecase.user.UserLoginUseCase
import com.example.snailpasswordmanager.domain.usecase.user.UserRegisterUseCase
import com.example.snailpasswordmanager.domain.usecase.user.UserUseCases
import com.example.snailpasswordmanager.data.retrofit2.ServerApi
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.usecase.cryptography.Decode
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    //@Singleton
    fun providerPasswordUseCases(repository: RecordListRepository, serverApi: ServerApi, userEntityAuth: AuthorizationData) : PasswordUseCases {
        return PasswordUseCases(
            getPasswordList = GetPasswordList(repository,userEntityAuth),
            deletePassword = DeletePassword(repository),
            insertPassword = InsertPassword(repository,userEntityAuth),
            editPassword = EditPassword(repository,userEntityAuth)
        )
    }
    /*@Provides
    @Singleton
    fun providerUserUseCases(repository: UserRepository, userEntity: UserEntity) : UserUseCases {
        return UserUseCases(
            userLoginUseCase = UserLoginUseCase(repository,userEntity),
            userRegisterUseCase = UserRegisterUseCase(repository)
        )
    }*/

    @Provides
    //@Singleton
    fun providerDecodeUseCase(userEntity: AuthorizationData) : Decode {
        return Decode(userEntity)
    }

    @Provides
    @Singleton
    fun providerUserEntity() : UserEntity {
        return UserEntity(
            UUID.fromString("0-0-0-0-0"),
            email = "1",
            password = "1",
            hint = "1"
        )
    }
/*

    @Provides
    @Singleton
    fun providerAuthInfo() : AuthInfo {
        return AuthInfo("-","-")
    }


    fun p1roviderUserUseCases(repository: UserRepository,serverApi: ServerApi) : UserUseCases {
        return UserUseCases(
            userLoginUseCase = UserLoginUseCase(repository,serverApi),
            userRegisterUseCase = UserRegisterUseCase(repository)
        )
    }*/
}