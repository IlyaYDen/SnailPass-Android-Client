package com.example.snailpasswordmanager.presentation.registration;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0010"}, d2 = {"Lcom/example/snailpasswordmanager/presentation/registration/RegistrationViewModel;", "Landroidx/lifecycle/ViewModel;", "userUseCases", "Lcom/example/snailpasswordmanager/domain/usecase/user/UserUseCases;", "(Lcom/example/snailpasswordmanager/domain/usecase/user/UserUseCases;)V", "token", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/snailpasswordmanager/retrofit2/Token;", "getToken", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "getUserUseCases", "()Lcom/example/snailpasswordmanager/domain/usecase/user/UserUseCases;", "registrationEvent", "", "userEntity", "Lcom/example/snailpasswordmanager/domain/model/UserEntity;", "app_debug"})
public final class RegistrationViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.snailpasswordmanager.domain.usecase.user.UserUseCases userUseCases = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.snailpasswordmanager.retrofit2.Token> token = null;
    
    @javax.inject.Inject()
    public RegistrationViewModel(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.usecase.user.UserUseCases userUseCases) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.snailpasswordmanager.domain.usecase.user.UserUseCases getUserUseCases() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.MutableStateFlow<com.example.snailpasswordmanager.retrofit2.Token> getToken() {
        return null;
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    public final void registrationEvent(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.model.UserEntity userEntity) {
    }
}