package com.example.snailpasswordmanager.domain.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\bH&J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\f"}, d2 = {"Lcom/example/snailpasswordmanager/domain/repository/UserRepository;", "", "addUser", "", "userEntity", "Lcom/example/snailpasswordmanager/domain/model/UserEntity;", "getUser", "userId", "", "getUserList", "", "removeUser", "app_debug"})
public abstract interface UserRepository {
    
    public abstract void addUser(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.model.UserEntity userEntity);
    
    public abstract void removeUser(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.model.UserEntity userEntity);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.example.snailpasswordmanager.domain.model.UserEntity> getUserList();
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.example.snailpasswordmanager.domain.model.UserEntity getUser(int userId);
}