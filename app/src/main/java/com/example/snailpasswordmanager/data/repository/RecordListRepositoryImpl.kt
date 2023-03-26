package com.example.snailpasswordmanager.data.repository

import android.util.Log
import com.example.snailpasswordmanager.LoginMode
import com.example.snailpasswordmanager.data.database.record.RecordAddFieldDao
import com.example.snailpasswordmanager.data.database.record.RecordDao
import com.example.snailpasswordmanager.data.model.RecordAddFieldEntityMapper
import com.example.snailpasswordmanager.data.model.RecordEntityMapper
import com.example.snailpasswordmanager.data.retrofit2.*
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.RecordListRepository
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.util.*
import javax.inject.Inject

class RecordListRepositoryImpl @Inject constructor(
    private val recordDao: RecordDao,
    private val recordAddFieldDao: RecordAddFieldDao,
    var serverApi: ServerApi,
    var userEntityAuth: AuthorizationData,
    ) : RecordListRepository {

    private val recordEntityMapper = RecordEntityMapper()
    private val recordAddFieldEntityMapper = RecordAddFieldEntityMapper()

    override suspend fun getRecordList(): Flow<List<RecordEntity>?> {

        if(userEntityAuth.loginMode == LoginMode.ONLINE) {
            try {
                val records = serverApi.getRecords()//token.token

                recordDao.deleteUserRecords(userEntityAuth.user.id)

                if (records != null) {
                    records.map {


                        recordDao.insertRecord(
                            recordEntityMapper.mapEntityToDbModel(
                                RecordEntity(
                                    id = UUID.fromString(it.id),
                                    name = it.name,
                                    login = it.login,
                                    encrypted_password = it.password,
                                    editedTime = it.edited_time,
                                    creationTime = it.creation_time,
                                    isdeleted = it.is_deleted,
                                    userId = it.user_id,
                                    isfavorite = it.is_favorite
                                )
                            )
                        )
                        recordAddFieldDao.deleteFieldById(it.id)
                        it.additional_fields.map { t ->
                            recordAddFieldDao.addField(recordAddFieldEntityMapper.mapEntityToDbModel(t))
                        }
                    }

                }
            } catch (e: HttpException) {
                //-Log.d("MYLOG_testER","FAIL serverApi.getRecords()")
                if (e.code() == 404) {

                    recordDao.deleteRecords()
                }
                return flow { //null edited 09
                }
            } catch (e: Exception) {


                return recordDao.getRecords().map {
                    recordEntityMapper.mapListDbModelToListEntity(it)
                }
            }
        }

        return recordDao.getRecords().map {
            recordEntityMapper.mapListDbModelToListEntity(it)
        }
    }

    override suspend fun getRecordById(id: Int): RecordEntity? {
        return recordDao.getRecordById(id)?.let { recordEntityMapper.mapDbModelToEntity(it) }
    }

    override suspend fun insertRecord(passwordEntity: RecordEntity) {

        try {
            //-Log.d("MYLOG_testE",passwordEntity.id.toString())
            serverApi.addRecord(
                //token.token,
                AddRecord(
                    passwordEntity.id.toString(),
                    passwordEntity.login,
                    passwordEntity.name,
                    passwordEntity.encrypted_password,
                    is_favorite = passwordEntity.isfavorite
                    //passwordEntity.nonce
                )
            )
        } catch (_: Exception){

        }

    }

    override suspend fun editRecord(passwordEntity: RecordEntity) {

        try {
            //-Log.d("MYLOG_testE",passwordEntity.id.toString())
            serverApi.editRecord(
                //token.token,
                Record(
                    creation_time = "",
                    edited_time= "",
                    password = passwordEntity.encrypted_password,
                    id = passwordEntity.id.toString(),
                    is_deleted = passwordEntity.isdeleted,
                    is_favorite = passwordEntity.isfavorite,
                    login = passwordEntity.login,
                    name = passwordEntity.name,
                    user_id = passwordEntity.userId
                    //passwordEntity.nonce
                )
            )
        } catch (_: Exception){

        }
    }

    override suspend fun deleteRecord(id: UUID) {
        try {
            serverApi.deleteRecord(id.toString())
            recordDao.deleteRecord(id)
        } catch (_: Exception){
        }
    }

}
