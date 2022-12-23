package com.example.snailpasswordmanager.presentation.mainscreen;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t0\bJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/example/snailpasswordmanager/presentation/mainscreen/MainListViewModel;", "Landroidx/lifecycle/ViewModel;", "passwordUseCases", "Lcom/example/snailpasswordmanager/domain/usecase/passwords/PasswordUseCases;", "(Lcom/example/snailpasswordmanager/domain/usecase/passwords/PasswordUseCases;)V", "recentlyDeletedPassword", "Lcom/example/snailpasswordmanager/domain/model/RecordEntity;", "getPasswords", "Lkotlinx/coroutines/flow/Flow;", "", "onEvent", "", "event", "Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent;", "updateDb", "app_debug"})
public final class MainListViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases passwordUseCases = null;
    private com.example.snailpasswordmanager.domain.model.RecordEntity recentlyDeletedPassword;
    
    public MainListViewModel(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases passwordUseCases) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.snailpasswordmanager.domain.model.RecordEntity>> getPasswords() {
        return null;
    }
    
    public final void onEvent(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.presentation.mainscreen.PasswordsEvent event) {
    }
    
    public final void updateDb() {
    }
}