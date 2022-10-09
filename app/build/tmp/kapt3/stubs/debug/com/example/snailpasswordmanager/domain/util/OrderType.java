package com.example.snailpasswordmanager.domain.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/snailpasswordmanager/domain/util/OrderType;", "", "()V", "Ascending", "Descending", "Lcom/example/snailpasswordmanager/domain/util/OrderType$Ascending;", "Lcom/example/snailpasswordmanager/domain/util/OrderType$Descending;", "app_debug"})
public abstract class OrderType {
    
    private OrderType() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/snailpasswordmanager/domain/util/OrderType$Ascending;", "Lcom/example/snailpasswordmanager/domain/util/OrderType;", "()V", "app_debug"})
    public static final class Ascending extends com.example.snailpasswordmanager.domain.util.OrderType {
        @org.jetbrains.annotations.NotNull()
        public static final com.example.snailpasswordmanager.domain.util.OrderType.Ascending INSTANCE = null;
        
        private Ascending() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/snailpasswordmanager/domain/util/OrderType$Descending;", "Lcom/example/snailpasswordmanager/domain/util/OrderType;", "()V", "app_debug"})
    public static final class Descending extends com.example.snailpasswordmanager.domain.util.OrderType {
        @org.jetbrains.annotations.NotNull()
        public static final com.example.snailpasswordmanager.domain.util.OrderType.Descending INSTANCE = null;
        
        private Descending() {
            super();
        }
    }
}