package com.example.snailpasswordmanager.data.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0005\u001a\u0002H\u0004H&\u00a2\u0006\u0002\u0010\u0006J!\u0010\u0007\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00032\u0006\u0010\b\u001a\u0002H\u0004H&\u00a2\u0006\u0002\u0010\u0006J\'\u0010\t\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00040\u000bH&\u00a2\u0006\u0002\u0010\f\u00a8\u0006\r"}, d2 = {"Lcom/example/snailpasswordmanager/data/model/Mapper;", "", "mapDbModelToEntity", "R", "T", "EntityDbModel", "(Ljava/lang/Object;)Ljava/lang/Object;", "mapEntityToDbModel", "passwordEntity", "mapListDbModelToListEntity", "list", "", "(Ljava/util/List;)Ljava/lang/Object;", "app_debug"})
public abstract interface Mapper {
    
    public abstract <T extends java.lang.Object, R extends java.lang.Object>R mapEntityToDbModel(T passwordEntity);
    
    public abstract <T extends java.lang.Object, R extends java.lang.Object>R mapDbModelToEntity(T EntityDbModel);
    
    public abstract <T extends java.lang.Object, R extends java.lang.Object>R mapListDbModelToListEntity(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends T> list);
}