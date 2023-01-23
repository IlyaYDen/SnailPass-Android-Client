package com.example.snailpasswordmanager.data.repository

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.data.database.record.RecordDao
import com.example.snailpasswordmanager.data.model.RecordEntityMapper
import com.example.snailpasswordmanager.data.retrofit2.*
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.repository.RecordListRepository
import com.example.snailpasswordmanager.presentation.mainscreen.PasswordsEvent
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.*
import javax.inject.Inject

class RecordListRepositoryImpl @Inject constructor(
    private val dao: RecordDao,
    var serverApi: ServerApi
    ) : RecordListRepository {

    private val mapper = RecordEntityMapper()

    override suspend fun getRecordList(): Flow<List<RecordEntity>?> {

        try {
            Log.d("MYLOG_testER"," serverApi.getRecords()")
            val records = serverApi.getRecords()//token.token


            Log.d("MYLOG_testER","SUCCESS serverApi.getRecords()")
            if (records != null) {
                records.map {

                    Log.d("MYLOG_testN",it.toString())


                    dao.insertRecord(
                        mapper.mapEntityToDbModel(
                            RecordEntity(
                                id = UUID.fromString(it.id),
                                name = it.name,
                                login = it.login,
                                encrypted_password = it.encrypted_password,
                                editedTime = it.edited_time,
                                creationTime = it.creation_time,
                                nonce = it.nonce,
                                userId = it.user_id,
                                isfavorite = it.is_favorite
                            )
                        )
                    )
                }
            }
        } catch (e : HttpException) {
            Log.d("MYLOG_testER","FAIL serverApi.getRecords()")
            return flow { null }
        }
            catch (e : Exception){

                Log.d("MYLOG_testER","FAIL serverApi.getRecords() Error")
                Log.d("MYLOG_testER"," " + e)

            return dao.getRecords().map {
                Log.d("MYLOG_testER","FAIL getRecordList from local: " + it.size)
                mapper.mapListDbModelToListEntity(it)
            }
        }


        return dao.getRecords().map {
            Log.d("MYLOG_test","noexeption: " + it.size)
            mapper.mapListDbModelToListEntity(it)
        }
        //return dao.getRecords().map {
        //    Log.d("MYLOG_test","getRecordList test: " + it.size)
        //    mapper.mapListDbModelToListEntity(it)
        //}
    }

    override suspend fun getRecordById(id: Int): RecordEntity? {
        return dao.getRecordById(id)?.let { mapper.mapDbModelToEntity(it) }
    }

    override suspend fun insertRecord(passwordEntity: RecordEntity) {

        try {
            serverApi.addRecord(
                //token.token,
                AddRecord(
                    passwordEntity.id.toString(),
                    passwordEntity.login,
                    passwordEntity.name,
                    passwordEntity.encrypted_password,
                    passwordEntity.nonce
                )
            )

            Log.d("MYLOG_test","id: " + passwordEntity.nonce)
            dao.insertRecord(mapper.mapEntityToDbModel(passwordEntity))
        } catch (_: Exception){

        }

    }

    override suspend fun deleteRecord(passwordEntity: RecordEntity) {
        try {
            Log.d("MYLOG_test","id: " + passwordEntity.id)
            serverApi.deleteRecord(
               // token.token,
                    passwordEntity.id.toString()

            )
            dao.deleteRecord(mapper.mapEntityToDbModel(passwordEntity))
        } catch (_: Exception){

        }
    }

}
