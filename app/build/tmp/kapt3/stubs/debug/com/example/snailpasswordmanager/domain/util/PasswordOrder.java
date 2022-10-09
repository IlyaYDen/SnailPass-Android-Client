package com.example.snailpasswordmanager.domain.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\t\n\u00a8\u0006\u000b"}, d2 = {"Lcom/example/snailpasswordmanager/domain/util/PasswordOrder;", "", "orderType", "Lcom/example/snailpasswordmanager/domain/util/OrderType;", "(Lcom/example/snailpasswordmanager/domain/util/OrderType;)V", "getOrderType", "()Lcom/example/snailpasswordmanager/domain/util/OrderType;", "Date", "Service", "Lcom/example/snailpasswordmanager/domain/util/PasswordOrder$Date;", "Lcom/example/snailpasswordmanager/domain/util/PasswordOrder$Service;", "app_debug"})
public abstract class PasswordOrder {
    @org.jetbrains.annotations.NotNull()
    private final com.example.snailpasswordmanager.domain.util.OrderType orderType = null;
    
    private PasswordOrder(com.example.snailpasswordmanager.domain.util.OrderType orderType) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.snailpasswordmanager.domain.util.OrderType getOrderType() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/example/snailpasswordmanager/domain/util/PasswordOrder$Service;", "Lcom/example/snailpasswordmanager/domain/util/PasswordOrder;", "orderType", "Lcom/example/snailpasswordmanager/domain/util/OrderType;", "(Lcom/example/snailpasswordmanager/domain/util/OrderType;)V", "app_debug"})
    public static final class Service extends com.example.snailpasswordmanager.domain.util.PasswordOrder {
        
        public Service(@org.jetbrains.annotations.NotNull()
        com.example.snailpasswordmanager.domain.util.OrderType orderType) {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/example/snailpasswordmanager/domain/util/PasswordOrder$Date;", "Lcom/example/snailpasswordmanager/domain/util/PasswordOrder;", "orderType", "Lcom/example/snailpasswordmanager/domain/util/OrderType;", "(Lcom/example/snailpasswordmanager/domain/util/OrderType;)V", "app_debug"})
    public static final class Date extends com.example.snailpasswordmanager.domain.util.PasswordOrder {
        
        public Date(@org.jetbrains.annotations.NotNull()
        com.example.snailpasswordmanager.domain.util.OrderType orderType) {
            super(null);
        }
    }
}