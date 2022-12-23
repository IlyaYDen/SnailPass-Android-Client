package com.example.snailpasswordmanager.domain.usecase.passwords

import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.repository.RecordListRepository
import com.example.snailpasswordmanager.domain.util.OrderType
import com.example.snailpasswordmanager.domain.util.PasswordOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPasswordList @Inject constructor(
    private val passwordListRepository: RecordListRepository
    ) {

    operator fun invoke (
        passwordOrder: PasswordOrder = PasswordOrder.Service(OrderType.Ascending)
    ) : Flow<List<RecordEntity>> {
        return passwordListRepository.getRecordList().map { passwords ->
            when(passwordOrder.orderType) {
                is OrderType.Ascending->{

                    when(passwordOrder){
                        is PasswordOrder.Service -> passwords.sortedBy { it.service.toLowerCase() }
                        is PasswordOrder.Date -> passwords.sortedBy { it.timestamp }
                    }
                }
                is OrderType.Descending->{

                    when(passwordOrder){
                        is PasswordOrder.Service -> passwords.sortedByDescending { it.service.toLowerCase() }
                        is PasswordOrder.Date -> passwords.sortedByDescending { it.timestamp }
                    }
                }
            }
        };
    }
}