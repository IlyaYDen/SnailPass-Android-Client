package com.example.snailpasswordmanager.di;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&\u00a8\u0006\f"}, d2 = {"Lcom/example/snailpasswordmanager/di/AppComponent;", "", "inject", "", "loginActivity", "Lcom/example/snailpasswordmanager/presentation/login/LoginActivity;", "mainListActivity", "Lcom/example/snailpasswordmanager/presentation/mainscreen/MainListActivity;", "passwordItemActivity", "Lcom/example/snailpasswordmanager/presentation/passworditem/PasswordItemActivity;", "registrationActivity", "Lcom/example/snailpasswordmanager/presentation/registration/RegistrationActivity;", "app_debug"})
@dagger.Component(modules = {com.example.snailpasswordmanager.di.AppModule.class, com.example.snailpasswordmanager.di.DomainModule.class, com.example.snailpasswordmanager.di.DataModule.class, com.example.snailpasswordmanager.di.RetrofitModule.class})
public abstract interface AppComponent {
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.presentation.mainscreen.MainListActivity mainListActivity);
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.presentation.passworditem.PasswordItemActivity passwordItemActivity);
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.presentation.registration.RegistrationActivity registrationActivity);
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.presentation.login.LoginActivity loginActivity);
}