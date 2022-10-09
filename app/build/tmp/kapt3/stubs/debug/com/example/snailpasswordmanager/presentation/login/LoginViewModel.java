package com.example.snailpasswordmanager.presentation.login;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000b"}, d2 = {"Lcom/example/snailpasswordmanager/presentation/login/LoginViewModel;", "Landroidx/lifecycle/ViewModel;", "logInUseCases", "Lcom/example/snailpasswordmanager/domain/usecase/user/UserUseCases;", "(Lcom/example/snailpasswordmanager/domain/usecase/user/UserUseCases;)V", "getLogInUseCases", "()Lcom/example/snailpasswordmanager/domain/usecase/user/UserUseCases;", "onEvent", "", "loginEvent", "Lcom/example/snailpasswordmanager/presentation/login/LoginEvent;", "app_debug"})
public final class LoginViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.snailpasswordmanager.domain.usecase.user.UserUseCases logInUseCases = null;
    
    @javax.inject.Inject()
    public LoginViewModel(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.usecase.user.UserUseCases logInUseCases) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.snailpasswordmanager.domain.usecase.user.UserUseCases getLogInUseCases() {
        return null;
    }
    
    public final boolean onEvent(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.presentation.login.LoginEvent loginEvent) {
        return false;
    }
}