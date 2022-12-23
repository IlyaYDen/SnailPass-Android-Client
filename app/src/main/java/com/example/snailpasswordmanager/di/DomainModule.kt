package com.example.snailpasswordmanager.di

import com.example.snailpasswordmanager.domain.repository.RecordListRepository
import com.example.snailpasswordmanager.domain.repository.UserRepository
import com.example.snailpasswordmanager.domain.usecase.passwords.*
import com.example.snailpasswordmanager.domain.usecase.user.UserLoginUseCase
import com.example.snailpasswordmanager.domain.usecase.user.UserRegisterUseCase
import com.example.snailpasswordmanager.domain.usecase.user.UserUseCases
import com.example.snailpasswordmanager.retrofit2.ServerApi
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    //@Singleton
    fun providerPasswordUseCases(repository: RecordListRepository, serverApi: ServerApi) : PasswordUseCases {
        return PasswordUseCases(
            getPasswordList = GetPasswordList(repository),
            deletePassword = DeletePassword(repository),
            insertPassword = InsertPassword(repository),
            updatePasswords = UpdatePasswords(repository,serverApi)
        )
    }
    @Provides
    //@Singleton
    fun providerUserUseCases(repository: UserRepository,serverApi: ServerApi) : UserUseCases {
        return UserUseCases(
            userLoginUseCase = UserLoginUseCase(repository,serverApi),
            userRegisterUseCase = UserRegisterUseCase(repository)
        )
    }
}