package com.example.snailpasswordmanager.presentation.mainscreen;

import java.lang.System;

@kotlin.Suppress(names = {"UNCHECKED_CAST"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J%\u0010\b\u001a\u0002H\t\"\b\b\u0000\u0010\t*\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\fH\u0016\u00a2\u0006\u0002\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004\u00a8\u0006\u000e"}, d2 = {"Lcom/example/snailpasswordmanager/presentation/mainscreen/MainListViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "passwordUseCases", "Lcom/example/snailpasswordmanager/domain/usecase/passwords/PasswordUseCases;", "(Lcom/example/snailpasswordmanager/domain/usecase/passwords/PasswordUseCases;)V", "getPasswordUseCases", "()Lcom/example/snailpasswordmanager/domain/usecase/passwords/PasswordUseCases;", "setPasswordUseCases", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "app_debug"})
public final class MainListViewModelFactory implements androidx.lifecycle.ViewModelProvider.Factory {
    @org.jetbrains.annotations.NotNull()
    private com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases passwordUseCases;
    
    @javax.inject.Inject()
    public MainListViewModelFactory(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases passwordUseCases) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases getPasswordUseCases() {
        return null;
    }
    
    public final void setPasswordUseCases(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public <T extends androidx.lifecycle.ViewModel>T create(@org.jetbrains.annotations.NotNull()
    java.lang.Class<T> modelClass) {
        return null;
    }
}