package com.example.snailpasswordmanager.data.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0016J\u0012\u0010\r\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000e\u001a\u00020\tH\u0016J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/example/snailpasswordmanager/data/repository/UserRepositoryImpl;", "Lcom/example/snailpasswordmanager/domain/repository/UserRepository;", "dao", "Lcom/example/snailpasswordmanager/data/database/user/UserDao;", "(Lcom/example/snailpasswordmanager/data/database/user/UserDao;)V", "UserList", "", "Lcom/example/snailpasswordmanager/domain/model/UserEntity;", "autoIncrementid", "", "addUser", "", "userEntity", "getUser", "userId", "getUserList", "", "removeUser", "app_debug"})
public final class UserRepositoryImpl implements com.example.snailpasswordmanager.domain.repository.UserRepository {
    private final com.example.snailpasswordmanager.data.database.user.UserDao dao = null;
    private final java.util.List<com.example.snailpasswordmanager.domain.model.UserEntity> UserList = null;
    private int autoIncrementid = 0;
    
    @javax.inject.Inject()
    public UserRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.data.database.user.UserDao dao) {
        super();
    }
    
    @java.lang.Override()
    public void addUser(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.model.UserEntity userEntity) {
    }
    
    @java.lang.Override()
    public void removeUser(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.model.UserEntity userEntity) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.List<com.example.snailpasswordmanager.domain.model.UserEntity> getUserList() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public com.example.snailpasswordmanager.domain.model.UserEntity getUser(int userId) {
        return null;
    }
}