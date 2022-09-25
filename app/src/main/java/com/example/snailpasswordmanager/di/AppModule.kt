package com.example.snailpasswordmanager.di

import android.app.Application
import androidx.room.Room
import com.example.snailpasswordmanager.data.database.PasswordsDb
import com.example.snailpasswordmanager.data.repository.PasswordListRepositoryImpl
import com.example.snailpasswordmanager.domain.repository.PasswordListRepository
import com.example.snailpasswordmanager.domain.usecase.passwords.DeletePassword
import com.example.snailpasswordmanager.domain.usecase.passwords.GetPasswordList
import com.example.snailpasswordmanager.domain.usecase.passwords.InsertPassword
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNote(app: Application): PasswordsDb {
        return synchronized(this) {
            Room.databaseBuilder(
                app,
                PasswordsDb::class.java,
                PasswordsDb.DATABASE_NAME
            ).build()
        }
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: PasswordsDb): PasswordListRepository{
        return PasswordListRepositoryImpl(db.dao)
    }


    @Provides
    @Singleton
    fun providerPasswordUseCases(repository: PasswordListRepository) : PasswordUseCases {
        return PasswordUseCases(
            getPasswordList = GetPasswordList(repository),
            deletePassword = DeletePassword(repository),
            insertPassword = InsertPassword(repository)
        )
    }
}