package com.example.snailpasswordmanager.di;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u0010H\u0007J\u0010\u0010\u0011\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\u0010H\u0007\u00a8\u0006\u0014"}, d2 = {"Lcom/example/snailpasswordmanager/di/DataModule;", "", "()V", "provideApplication", "Landroid/app/Application;", "providePasswordDao", "Lcom/example/snailpasswordmanager/data/database/password/PassowrdDao;", "db", "Lcom/example/snailpasswordmanager/data/database/password/PasswordDb;", "providePasswordDb", "context", "Landroid/content/Context;", "providePasswordListRepository", "Lcom/example/snailpasswordmanager/domain/repository/PasswordListRepository;", "provideUserDao", "Lcom/example/snailpasswordmanager/data/database/user/UserDao;", "Lcom/example/snailpasswordmanager/data/database/user/UserDb;", "provideUserDb", "provideUserRepository", "Lcom/example/snailpasswordmanager/domain/repository/UserRepository;", "app_debug"})
@dagger.Module()
public final class DataModule {
    
    public DataModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public final com.example.snailpasswordmanager.data.database.password.PasswordDb providePasswordDb(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public final com.example.snailpasswordmanager.data.database.user.UserDb provideUserDb(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public final android.app.Application provideApplication() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public final com.example.snailpasswordmanager.domain.repository.PasswordListRepository providePasswordListRepository(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.data.database.password.PasswordDb db) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final com.example.snailpasswordmanager.data.database.password.PassowrdDao providePasswordDao(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.data.database.password.PasswordDb db) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public final com.example.snailpasswordmanager.domain.repository.UserRepository provideUserRepository(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.data.database.user.UserDb db) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final com.example.snailpasswordmanager.data.database.user.UserDao provideUserDao(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.data.database.user.UserDb db) {
        return null;
    }
}