package com.example.snailpasswordmanager.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.snailpasswordmanager.data.database.record.RecordDao
import com.example.snailpasswordmanager.data.database.record.RecordDb
import com.example.snailpasswordmanager.data.database.record.UserDao
import com.example.snailpasswordmanager.data.repository.RecordListRepositoryImpl
import com.example.snailpasswordmanager.data.repository.UserRepositoryImpl
import com.example.snailpasswordmanager.data.retrofit2.ServerApi
import com.example.snailpasswordmanager.data.retrofit2.Token
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.RecordListRepository
import com.example.snailpasswordmanager.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {


    @Provides
    //@Singleton
    fun provideApplication(): Application {
        return Application()
    }
    @Provides
    @Singleton
    fun provideToken(): Token {
        return Token("-")
    }



    @Provides
    @Singleton
    fun providePasswordDb(context: Context): RecordDb {
        return synchronized(this) {

            var dbBuilder = Room.databaseBuilder(
                context,
                RecordDb::class.java,
                RecordDb.DATABASE_NAME
            ).build()
            dbBuilder
        }
    }


    @Provides
    //@Singleton
    fun providePasswordListRepository(db: RecordDb,serverApi: ServerApi): RecordListRepository {
        return RecordListRepositoryImpl(db.recordDao,serverApi)
    }

    @Provides
    @Singleton
    fun providePasswordDao(db: RecordDb): RecordDao {
        return db.recordDao
    }


    @Provides
    //@Singleton
    fun provideUserRepository(db: RecordDb, serverApi: ServerApi, token: Token, userEntityAuth: UserEntity): UserRepository {
        return UserRepositoryImpl(db.userDao, serverApi, userEntityAuth,token)
    }

    @Provides
    @Singleton
    fun provideUserDao(db: RecordDb): UserDao {
        return db.userDao
    }

}