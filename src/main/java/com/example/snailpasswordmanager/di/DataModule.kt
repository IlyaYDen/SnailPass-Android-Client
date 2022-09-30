package com.example.snailpasswordmanager.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.snailpasswordmanager.data.database.PassowrdDao
import com.example.snailpasswordmanager.data.database.PasswordsDb
import com.example.snailpasswordmanager.data.repository.PasswordListRepositoryImpl
import com.example.snailpasswordmanager.domain.repository.PasswordListRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    //@Singleton
    fun providePasswordDb(context: Context): PasswordsDb {
        return synchronized(this) {
            Room.databaseBuilder(
                context,
                PasswordsDb::class.java,
                PasswordsDb.DATABASE_NAME
            ).build()
        }
    }

    @Provides
    //@Singleton
    fun provideApplication(): Application {
        return Application()
    }

    @Provides
    //@Singleton
    fun providePasswordListRepository(db: PasswordsDb): PasswordListRepository {
        return PasswordListRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun providePasswordDao(db: PasswordsDb): PassowrdDao {
        return db.dao
    }
}