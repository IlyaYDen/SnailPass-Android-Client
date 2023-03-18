package com.example.snailpasswordmanager.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.snailpasswordmanager.LoginMode
import com.example.snailpasswordmanager.data.database.record.*
import com.example.snailpasswordmanager.data.repository.*
import com.example.snailpasswordmanager.data.retrofit2.ServerApi
import com.example.snailpasswordmanager.data.retrofit2.Token
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.AdditionalFieldsRepository
import com.example.snailpasswordmanager.domain.repository.NoteListRepository
import com.example.snailpasswordmanager.domain.repository.RecordListRepository
import com.example.snailpasswordmanager.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import java.util.*
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
    fun providePasswordListRepository(db: RecordDb,serverApi: ServerApi, userEntityAuth: AuthorizationData): RecordListRepository {
        return RecordListRepositoryImpl(db.recordDao,db.recordAddFieldDao,serverApi, userEntityAuth)
    }

    @Provides
    //@Singleton
    fun provideNoteListRepository(db: RecordDb,serverApi: ServerApi,userEntityAuth: AuthorizationData): NoteListRepository {
        return NoteListRepositoryImpl(serverApi,db.noteDao,userEntityAuth)
    }
    @Provides
    //@Singleton
    fun provideAdditionalFieldsRepository(serverApi: ServerApi,recordAddFieldDao: RecordAddFieldDao,userEntityAuth: AuthorizationData): AdditionalFieldsRepository {
        return AdditionalFieldsRepositoryImpl(serverApi,recordAddFieldDao,userEntityAuth)
    }

    @Provides
    //@Singleton
    fun provideUserRepository(db: RecordDb, serverApi: ServerApi, token: Token, userEntityAuth: AuthorizationData): UserRepository {
        return UserRepositoryImpl(db.userDao, serverApi, userEntityAuth,token)
    }



    @Provides
    @Singleton
    fun provideNoteDao(db: RecordDb): NoteDao {
        return db.noteDao
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


    @Provides
    @Singleton
    fun provideAuthorizationData(): AuthorizationData {
        return AuthorizationData(
            UserEntity(UUID.randomUUID(),"","","",false),LoginMode.ERROR)
    }

}