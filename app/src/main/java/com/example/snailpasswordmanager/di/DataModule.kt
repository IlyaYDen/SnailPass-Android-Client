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

            val MIGRATION_4_5 = object : Migration(4, 5) {
                override fun migrate(database: SupportSQLiteDatabase) {

                    //database.execSQL("ALTER TABLE users RENAME TO temp_users")
                    // Create the new table with the updated schema
                    database.execSQL("CREATE TABLE users_ (id TEXT NOT NULL, email TEXT NOT NULL, password TEXT NOT NULL, hint TEXT, is_admin INTEGER NOT NULL, PRIMARY KEY(id), UNIQUE(email))")

                    database.execSQL("CREATE UNIQUE INDEX index_users ON users_(email)")
                    // Copy the data from the old table to the new table
                    database.execSQL("INSERT INTO users_ (id, email, password, hint, is_admin) SELECT id, email, password, hint, is_admin FROM users")
                    // Drop the old table
                    database.execSQL("ALTER TABLE users RENAME TO temp_users")
                    database.execSQL("ALTER TABLE users_ RENAME TO users")
                    database.execSQL("DROP TABLE temp_users")
                }
            }
            val dbBuilder = Room.databaseBuilder(
                context,//	yury.v.denisov@gmail.com
                RecordDb::class.java,
                RecordDb.DATABASE_NAME
            )
                .addMigrations(MIGRATION_4_5)
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