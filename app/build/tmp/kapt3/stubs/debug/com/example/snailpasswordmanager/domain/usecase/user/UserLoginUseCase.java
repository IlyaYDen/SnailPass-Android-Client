package com.example.snailpasswordmanager.domain.usecase.user;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0087B\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lcom/example/snailpasswordmanager/domain/usecase/user/UserLoginUseCase;", "", "userRepository", "Lcom/example/snailpasswordmanager/domain/repository/UserRepository;", "serverApi", "Lcom/example/snailpasswordmanager/retrofit2/ServerApi;", "(Lcom/example/snailpasswordmanager/domain/repository/UserRepository;Lcom/example/snailpasswordmanager/retrofit2/ServerApi;)V", "getServerApi", "()Lcom/example/snailpasswordmanager/retrofit2/ServerApi;", "setServerApi", "(Lcom/example/snailpasswordmanager/retrofit2/ServerApi;)V", "invoke", "", "userEntity", "Lcom/example/snailpasswordmanager/domain/model/UserEntity;", "(Lcom/example/snailpasswordmanager/domain/model/UserEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class UserLoginUseCase {
    private final com.example.snailpasswordmanager.domain.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private com.example.snailpasswordmanager.retrofit2.ServerApi serverApi;
    
    public UserLoginUseCase(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.repository.UserRepository userRepository, @org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.retrofit2.ServerApi serverApi) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.snailpasswordmanager.retrofit2.ServerApi getServerApi() {
        return null;
    }
    
    public final void setServerApi(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.retrofit2.ServerApi p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.model.UserEntity userEntity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> continuation) {
        return null;
    }
}