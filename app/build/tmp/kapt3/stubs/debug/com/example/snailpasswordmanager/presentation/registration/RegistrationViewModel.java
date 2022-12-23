package com.example.snailpasswordmanager.presentation.registration;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0019\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcom/example/snailpasswordmanager/presentation/registration/RegistrationViewModel;", "Landroidx/lifecycle/ViewModel;", "userUseCases", "Lcom/example/snailpasswordmanager/domain/usecase/user/UserUseCases;", "(Lcom/example/snailpasswordmanager/domain/usecase/user/UserUseCases;)V", "getUserUseCases", "()Lcom/example/snailpasswordmanager/domain/usecase/user/UserUseCases;", "onEvent", "", "loginEvent", "Lcom/example/snailpasswordmanager/presentation/registration/RegistrationEvent;", "registrationEvent", "userEntity", "Lcom/example/snailpasswordmanager/domain/model/UserEntity;", "(Lcom/example/snailpasswordmanager/domain/model/UserEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class RegistrationViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.snailpasswordmanager.domain.usecase.user.UserUseCases userUseCases = null;
    
    @javax.inject.Inject()
    public RegistrationViewModel(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.usecase.user.UserUseCases userUseCases) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.snailpasswordmanager.domain.usecase.user.UserUseCases getUserUseCases() {
        return null;
    }
    
    public final void onEvent(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.presentation.registration.RegistrationEvent loginEvent) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object registrationEvent(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.model.UserEntity userEntity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}