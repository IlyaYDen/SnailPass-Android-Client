package com.example.snailpasswordmanager.domain.usecase.passwords;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001f\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/snailpasswordmanager/domain/usecase/passwords/GetPasswordList;", "", "passwordListRepository", "Lcom/example/snailpasswordmanager/domain/repository/RecordListRepository;", "(Lcom/example/snailpasswordmanager/domain/repository/RecordListRepository;)V", "invoke", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/snailpasswordmanager/domain/model/RecordEntity;", "passwordOrder", "Lcom/example/snailpasswordmanager/domain/util/PasswordOrder;", "app_debug"})
public final class GetPasswordList {
    private final com.example.snailpasswordmanager.domain.repository.RecordListRepository passwordListRepository = null;
    
    @javax.inject.Inject()
    public GetPasswordList(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.repository.RecordListRepository passwordListRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.snailpasswordmanager.domain.model.RecordEntity>> invoke(@org.jetbrains.annotations.NotNull()
    com.example.snailpasswordmanager.domain.util.PasswordOrder passwordOrder) {
        return null;
    }
}