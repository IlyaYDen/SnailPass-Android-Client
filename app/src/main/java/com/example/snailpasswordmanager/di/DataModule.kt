package com.example.snailpasswordmanager.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.snailpasswordmanager.data.database.record.RecordDao
import com.example.snailpasswordmanager.data.database.record.RecordDb
import com.example.snailpasswordmanager.data.database.user.UserDao
import com.example.snailpasswordmanager.data.database.user.UserDb
import com.example.snailpasswordmanager.data.repository.RecordListRepositoryImpl
import com.example.snailpasswordmanager.data.repository.UserRepositoryImpl
import com.example.snailpasswordmanager.domain.repository.RecordListRepository
import com.example.snailpasswordmanager.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    //@Singleton
    fun providePasswordDb(context: Context): RecordDb {
        return synchronized(this) {
            Room.databaseBuilder(
                context,
                RecordDb::class.java,
                RecordDb.DATABASE_NAME
            ).build()
        }
    }
    @Provides
    //@Singleton
    fun provideUserDb(context: Context): UserDb {
        return synchronized(this) {
            Room.databaseBuilder(
                context,
                UserDb::class.java,
                RecordDb.DATABASE_NAME
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
    fun providePasswordListRepository(db: RecordDb): RecordListRepository {
        return RecordListRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun providePasswordDao(db: RecordDb): RecordDao {
        return db.dao
    }


    @Provides
    //@Singleton
    fun provideUserRepository(db: UserDb): UserRepository {
        return UserRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideUserDao(db: UserDb): UserDao {
        return db.dao
    }
}