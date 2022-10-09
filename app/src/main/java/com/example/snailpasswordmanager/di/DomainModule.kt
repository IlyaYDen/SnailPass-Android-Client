package com.example.snailpasswordmanager.di

import com.example.snailpasswordmanager.domain.repository.PasswordListRepository
import com.example.snailpasswordmanager.domain.repository.UserRepository
import com.example.snailpasswordmanager.domain.usecase.passwords.DeletePassword
import com.example.snailpasswordmanager.domain.usecase.passwords.GetPasswordList
import com.example.snailpasswordmanager.domain.usecase.passwords.InsertPassword
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import com.example.snailpasswordmanager.domain.usecase.user.UserLoginUseCase
import com.example.snailpasswordmanager.domain.usecase.user.UserUseCases
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    //@Singleton
    fun providerPasswordUseCases(repository: PasswordListRepository) : PasswordUseCases {
        return PasswordUseCases(
            getPasswordList = GetPasswordList(repository),
            deletePassword = DeletePassword(repository),
            insertPassword = InsertPassword(repository)
        )
    }
    @Provides
    //@Singleton
    fun providerUserUseCases(repository: UserRepository) : UserUseCases {
        return UserUseCases(
            userLoginUseCase = UserLoginUseCase(repository)
        )
    }
}