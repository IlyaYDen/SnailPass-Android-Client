package com.example.snailpasswordmanager.data.database.password;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J\u0019\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcom/example/snailpasswordmanager/data/database/password/PassowrdDao;", "", "deletePassword", "", "passwordEntityDbModel", "Lcom/example/snailpasswordmanager/data/model/PasswordEntityDbModel;", "(Lcom/example/snailpasswordmanager/data/model/PasswordEntityDbModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPasswordById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPasswords", "Lkotlinx/coroutines/flow/Flow;", "", "insertPassword", "app_debug"})
public abstract interface PassowrdDao {
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM passwords")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.snailpasswordmanager.data.model.PasswordEntityDbModel>> getPasswords();
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "SELECT * FROM passwords WHERE id = :id")
    public abstract java.lang.Object getPasswordById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.snailpasswordmanager.data.model.PasswordEntityDbModel> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract java.lang.Object insertPassword(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.data.model.PasswordEntityDbModel passwordEntityDbModel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Delete()
    public abstract java.lang.Object deletePassword(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.data.model.PasswordEntityDbModel passwordEntityDbModel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}