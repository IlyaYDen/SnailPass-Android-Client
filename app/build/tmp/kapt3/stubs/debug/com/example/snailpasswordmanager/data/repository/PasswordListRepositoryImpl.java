package com.example.snailpasswordmanager.data.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u001b\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u000eH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0014\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00120\u0011H\u0016J\u0019\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0014"}, d2 = {"Lcom/example/snailpasswordmanager/data/repository/PasswordListRepositoryImpl;", "Lcom/example/snailpasswordmanager/domain/repository/PasswordListRepository;", "dao", "Lcom/example/snailpasswordmanager/data/database/password/PassowrdDao;", "(Lcom/example/snailpasswordmanager/data/database/password/PassowrdDao;)V", "mapper", "Lcom/example/snailpasswordmanager/data/model/PasswordEntityMapper;", "deletePassword", "", "passwordEntity", "Lcom/example/snailpasswordmanager/domain/model/PasswordEntity;", "(Lcom/example/snailpasswordmanager/domain/model/PasswordEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPasswordById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPasswordList", "Lkotlinx/coroutines/flow/Flow;", "", "insertPassword", "app_debug"})
public final class PasswordListRepositoryImpl implements com.example.snailpasswordmanager.domain.repository.PasswordListRepository {
    private final com.example.snailpasswordmanager.data.database.password.PassowrdDao dao = null;
    private final com.example.snailpasswordmanager.data.model.PasswordEntityMapper mapper = null;
    
    @javax.inject.Inject()
    public PasswordListRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.data.database.password.PassowrdDao dao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.snailpasswordmanager.domain.model.PasswordEntity>> getPasswordList() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getPasswordById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.snailpasswordmanager.domain.model.PasswordEntity> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object insertPassword(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.model.PasswordEntity passwordEntity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object deletePassword(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.model.PasswordEntity passwordEntity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}