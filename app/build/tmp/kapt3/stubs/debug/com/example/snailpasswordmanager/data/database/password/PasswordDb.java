package com.example.snailpasswordmanager.data.database.password;

import java.lang.System;

@androidx.room.Database(entities = {com.example.snailpasswordmanager.data.model.PasswordEntityDbModel.class}, version = 1, exportSchema = false)
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\'\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005\u00a2\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\b"}, d2 = {"Lcom/example/snailpasswordmanager/data/database/password/PasswordDb;", "Landroidx/room/RoomDatabase;", "()V", "dao", "Lcom/example/snailpasswordmanager/data/database/password/PassowrdDao;", "getDao", "()Lcom/example/snailpasswordmanager/data/database/password/PassowrdDao;", "Companion", "app_debug"})
public abstract class PasswordDb extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.snailpasswordmanager.data.database.password.PasswordDb.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DATABASE_NAME = "passwords_db";
    
    public PasswordDb() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.snailpasswordmanager.data.database.password.PassowrdDao getDao();
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/snailpasswordmanager/data/database/password/PasswordDb$Companion;", "", "()V", "DATABASE_NAME", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}