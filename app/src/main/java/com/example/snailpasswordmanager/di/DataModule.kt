package com.example.snailpasswordmanager.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.snailpasswordmanager.data.database.record.RecordAddFieldDao
import com.example.snailpasswordmanager.data.database.record.RecordDao
import com.example.snailpasswordmanager.data.database.record.RecordDb
import com.example.snailpasswordmanager.data.database.record.UserDao
import com.example.snailpasswordmanager.data.repository.AdditionalFieldsRepositoryImpl
import com.example.snailpasswordmanager.data.repository.RecordListRepositoryImpl
import com.example.snailpasswordmanager.data.repository.UserRepositoryImpl
import com.example.snailpasswordmanager.data.retrofit2.ServerApi
import com.example.snailpasswordmanager.data.retrofit2.Token
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.AdditionalFieldsRepository
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

           // val MIGRATION_1_2 = object : Migration(1, 2) {
           //     override fun migrate(database: SupportSQLiteDatabase) {
           //         database.execSQL("ALTER TABLE RecordEntityDbModel DROP COLUMN nonce")
           //     }
           // }
            val dbBuilder = Room.databaseBuilder(
                context,
                RecordDb::class.java,
                RecordDb.DATABASE_NAME
            )
                //.addMigrations(MIGRATION_1_2)
                .build()
            dbBuilder
        }
    }


    @Provides
    //@Singleton
    fun providePasswordListRepository(db: RecordDb,serverApi: ServerApi): RecordListRepository {
        return RecordListRepositoryImpl(db.recordDao,serverApi)
    }

    @Provides
    //@Singleton
    fun provideAdditionalFieldsRepository(serverApi: ServerApi,recordAddFieldDao: RecordAddFieldDao): AdditionalFieldsRepository {
        return AdditionalFieldsRepositoryImpl(serverApi,recordAddFieldDao)
    }

    @Provides
    //@Singleton
    fun provideUserRepository(db: RecordDb, serverApi: ServerApi, token: Token, userEntityAuth: UserEntity): UserRepository {
        return UserRepositoryImpl(db.userDao, serverApi, userEntityAuth,token)
    }


    @Provides
    @Singleton
    fun providePasswordDao(db: RecordDb): RecordDao {
        return db.recordDao
    }

    @Provides
    @Singleton
    fun provideUserDao(db: RecordDb): UserDao {
        return db.userDao
    }

    @Provides
    @Singleton
    fun provideFieldDao(db: RecordDb): RecordAddFieldDao {
        return db.recordAddFieldDao
    }

}