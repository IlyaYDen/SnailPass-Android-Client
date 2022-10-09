package com.example.snailpasswordmanager.presentation.mainscreen;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\bH\u00c6\u0003J-\u0010\u0012\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0019"}, d2 = {"Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsState;", "", "passwords", "", "Lcom/example/snailpasswordmanager/domain/model/PasswordEntity;", "passwordOrder", "Lcom/example/snailpasswordmanager/domain/util/PasswordOrder;", "isOrderSessionVisible", "", "(Ljava/util/List;Lcom/example/snailpasswordmanager/domain/util/PasswordOrder;Z)V", "()Z", "getPasswordOrder", "()Lcom/example/snailpasswordmanager/domain/util/PasswordOrder;", "getPasswords", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "app_debug"})
public final class PasswordsState {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.snailpasswordmanager.domain.model.PasswordEntity> passwords = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.snailpasswordmanager.domain.util.PasswordOrder passwordOrder = null;
    private final boolean isOrderSessionVisible = false;
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.snailpasswordmanager.presentation.mainscreen.PasswordsState copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.snailpasswordmanager.domain.model.PasswordEntity> passwords, @org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.util.PasswordOrder passwordOrder, boolean isOrderSessionVisible) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public PasswordsState() {
        super();
    }
    
    public PasswordsState(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.snailpasswordmanager.domain.model.PasswordEntity> passwords, @org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.util.PasswordOrder passwordOrder, boolean isOrderSessionVisible) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.snailpasswordmanager.domain.model.PasswordEntity> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.snailpasswordmanager.domain.model.PasswordEntity> getPasswords() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.snailpasswordmanager.domain.util.PasswordOrder component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.snailpasswordmanager.domain.util.PasswordOrder getPasswordOrder() {
        return null;
    }
    
    public final boolean component3() {
        return false;
    }
    
    public final boolean isOrderSessionVisible() {
        return false;
    }
}