package com.example.snailpasswordmanager.presentation.passworditem;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/example/snailpasswordmanager/presentation/passworditem/PasswordViewModel;", "Landroidx/lifecycle/ViewModel;", "passwordUseCases", "Lcom/example/snailpasswordmanager/domain/usecase/passwords/PasswordUseCases;", "(Lcom/example/snailpasswordmanager/domain/usecase/passwords/PasswordUseCases;)V", "addPassword", "", "passwordEntity", "Lcom/example/snailpasswordmanager/domain/model/PasswordEntity;", "deletePassword", "app_debug"})
public final class PasswordViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases passwordUseCases = null;
    
    public PasswordViewModel(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases passwordUseCases) {
        super();
    }
    
    public final void addPassword(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.model.PasswordEntity passwordEntity) {
    }
    
    public final void deletePassword(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.model.PasswordEntity passwordEntity) {
    }
}