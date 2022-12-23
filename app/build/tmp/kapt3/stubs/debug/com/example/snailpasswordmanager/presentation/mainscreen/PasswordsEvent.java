package com.example.snailpasswordmanager.presentation.mainscreen;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0007\u0003\u0004\u0005\u0006\u0007\b\tB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0007\n\u000b\f\r\u000e\u000f\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent;", "", "()V", "AddPassword", "DeletePassword", "GetPasswordsList", "Order", "RestorePassword", "ToggleOrderSection", "UpdateDb", "Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent$AddPassword;", "Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent$DeletePassword;", "Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent$GetPasswordsList;", "Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent$Order;", "Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent$RestorePassword;", "Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent$ToggleOrderSection;", "Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent$UpdateDb;", "app_debug"})
public abstract class PasswordsEvent {
    
    private PasswordsEvent() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent$Order;", "Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent;", "passwordOrder", "Lcom/example/snailpasswordmanager/domain/util/PasswordOrder;", "(Lcom/example/snailpasswordmanager/domain/util/PasswordOrder;)V", "getPasswordOrder", "()Lcom/example/snailpasswordmanager/domain/util/PasswordOrder;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class Order extends com.example.snailpasswordmanager.presentation.mainscreen.PasswordsEvent {
        @org.jetbrains.annotations.NotNull()
        private final com.example.snailpasswordmanager.domain.util.PasswordOrder passwordOrder = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.snailpasswordmanager.presentation.mainscreen.PasswordsEvent.Order copy(@org.jetbrains.annotations.NotNull()
        com.example.snailpasswordmanager.domain.util.PasswordOrder passwordOrder) {
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
        
        public Order(@org.jetbrains.annotations.NotNull()
        com.example.snailpasswordmanager.domain.util.PasswordOrder passwordOrder) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.snailpasswordmanager.domain.util.PasswordOrder component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.snailpasswordmanager.domain.util.PasswordOrder getPasswordOrder() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent$DeletePassword;", "Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent;", "passwordEntity", "Lcom/example/snailpasswordmanager/domain/model/RecordEntity;", "(Lcom/example/snailpasswordmanager/domain/model/RecordEntity;)V", "getPasswordEntity", "()Lcom/example/snailpasswordmanager/domain/model/RecordEntity;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class DeletePassword extends com.example.snailpasswordmanager.presentation.mainscreen.PasswordsEvent {
        @org.jetbrains.annotations.NotNull()
        private final com.example.snailpasswordmanager.domain.model.RecordEntity passwordEntity = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.snailpasswordmanager.presentation.mainscreen.PasswordsEvent.DeletePassword copy(@org.jetbrains.annotations.NotNull()
        com.example.snailpasswordmanager.domain.model.RecordEntity passwordEntity) {
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
        
        public DeletePassword(@org.jetbrains.annotations.NotNull()
        com.example.snailpasswordmanager.domain.model.RecordEntity passwordEntity) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.snailpasswordmanager.domain.model.RecordEntity component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.snailpasswordmanager.domain.model.RecordEntity getPasswordEntity() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent$AddPassword;", "Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent;", "passwordEntity", "Lcom/example/snailpasswordmanager/domain/model/RecordEntity;", "(Lcom/example/snailpasswordmanager/domain/model/RecordEntity;)V", "getPasswordEntity", "()Lcom/example/snailpasswordmanager/domain/model/RecordEntity;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class AddPassword extends com.example.snailpasswordmanager.presentation.mainscreen.PasswordsEvent {
        @org.jetbrains.annotations.NotNull()
        private final com.example.snailpasswordmanager.domain.model.RecordEntity passwordEntity = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.snailpasswordmanager.presentation.mainscreen.PasswordsEvent.AddPassword copy(@org.jetbrains.annotations.NotNull()
        com.example.snailpasswordmanager.domain.model.RecordEntity passwordEntity) {
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
        
        public AddPassword(@org.jetbrains.annotations.NotNull()
        com.example.snailpasswordmanager.domain.model.RecordEntity passwordEntity) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.snailpasswordmanager.domain.model.RecordEntity component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.snailpasswordmanager.domain.model.RecordEntity getPasswordEntity() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent$UpdateDb;", "Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent;", "passwordEntity", "Lcom/example/snailpasswordmanager/domain/model/RecordEntity;", "(Lcom/example/snailpasswordmanager/domain/model/RecordEntity;)V", "getPasswordEntity", "()Lcom/example/snailpasswordmanager/domain/model/RecordEntity;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class UpdateDb extends com.example.snailpasswordmanager.presentation.mainscreen.PasswordsEvent {
        @org.jetbrains.annotations.NotNull()
        private final com.example.snailpasswordmanager.domain.model.RecordEntity passwordEntity = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.snailpasswordmanager.presentation.mainscreen.PasswordsEvent.UpdateDb copy(@org.jetbrains.annotations.NotNull()
        com.example.snailpasswordmanager.domain.model.RecordEntity passwordEntity) {
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
        
        public UpdateDb(@org.jetbrains.annotations.NotNull()
        com.example.snailpasswordmanager.domain.model.RecordEntity passwordEntity) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.snailpasswordmanager.domain.model.RecordEntity component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.snailpasswordmanager.domain.model.RecordEntity getPasswordEntity() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent$GetPasswordsList;", "Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent;", "()V", "app_debug"})
    public static final class GetPasswordsList extends com.example.snailpasswordmanager.presentation.mainscreen.PasswordsEvent {
        @org.jetbrains.annotations.NotNull()
        public static final com.example.snailpasswordmanager.presentation.mainscreen.PasswordsEvent.GetPasswordsList INSTANCE = null;
        
        private GetPasswordsList() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent$RestorePassword;", "Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent;", "()V", "app_debug"})
    public static final class RestorePassword extends com.example.snailpasswordmanager.presentation.mainscreen.PasswordsEvent {
        @org.jetbrains.annotations.NotNull()
        public static final com.example.snailpasswordmanager.presentation.mainscreen.PasswordsEvent.RestorePassword INSTANCE = null;
        
        private RestorePassword() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent$ToggleOrderSection;", "Lcom/example/snailpasswordmanager/presentation/mainscreen/PasswordsEvent;", "()V", "app_debug"})
    public static final class ToggleOrderSection extends com.example.snailpasswordmanager.presentation.mainscreen.PasswordsEvent {
        @org.jetbrains.annotations.NotNull()
        public static final com.example.snailpasswordmanager.presentation.mainscreen.PasswordsEvent.ToggleOrderSection INSTANCE = null;
        
        private ToggleOrderSection() {
            super();
        }
    }
}