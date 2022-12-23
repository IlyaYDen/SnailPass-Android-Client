package com.example.snailpasswordmanager.di;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0007\u00a8\u0006\f"}, d2 = {"Lcom/example/snailpasswordmanager/di/DomainModule;", "", "()V", "providerPasswordUseCases", "Lcom/example/snailpasswordmanager/domain/usecase/passwords/PasswordUseCases;", "repository", "Lcom/example/snailpasswordmanager/domain/repository/RecordListRepository;", "serverApi", "Lcom/example/snailpasswordmanager/retrofit2/ServerApi;", "providerUserUseCases", "Lcom/example/snailpasswordmanager/domain/usecase/user/UserUseCases;", "Lcom/example/snailpasswordmanager/domain/repository/UserRepository;", "app_debug"})
@dagger.Module()
public final class DomainModule {
    
    public DomainModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public final com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases providerPasswordUseCases(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.repository.RecordListRepository repository, @org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.retrofit2.ServerApi serverApi) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public final com.example.snailpasswordmanager.domain.usecase.user.UserUseCases providerUserUseCases(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.repository.UserRepository repository, @org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.retrofit2.ServerApi serverApi) {
        return null;
    }
}