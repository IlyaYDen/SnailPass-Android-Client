package com.example.snailpasswordmanager.data.repository

import android.util.Log
import com.example.snailpasswordmanager.data.database.record.RecordDao
import com.example.snailpasswordmanager.data.model.RecordEntityMapper
import com.example.snailpasswordmanager.data.retrofit2.*
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.repository.RecordListRepository
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.util.*
import javax.inject.Inject

class RecordListRepositoryImpl @Inject constructor(
    private val recordDao: RecordDao,
    var serverApi: ServerApi
    ) : RecordListRepository {

    private val recordEntityMapper = RecordEntityMapper()

    override suspend fun getRecordList(): Flow<List<RecordEntity>?> {

        try {
            val records = serverApi.getRecords()//token.token

            recordDao.deleteRecords()

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
                                //nonce = it.nonce,
                                userId = it.user_id,
                                isfavorite = it.is_favorite
                            )
                        )
                    )
                }

            }
        } catch (e : HttpException) {
            //-Log.d("MYLOG_testER","FAIL serverApi.getRecords()")
            if(e.code() == 404){

                recordDao.deleteRecords()
            }
            return flow { //null edited 09
            }
        }
            catch (e : Exception){

                //Сделать утилиту с состояниями

                //-Log.d("MYLOG_testER","FAIL serverApi.getRecords() Error")
                //-Log.d("MYLOG_testER"," " + e)

            return recordDao.getRecords().map {
                //-Log.d("MYLOG_testER","FAIL getRecordList from local: " + it.size)
                recordEntityMapper.mapListDbModelToListEntity(it)
            }
        }


        return recordDao.getRecords().map {
            //-Log.d("MYLOG_test","noexeption: " + it.size)
            recordEntityMapper.mapListDbModelToListEntity(it)
        }
        //return dao.getRecords().map {
        //    //-Log.d("MYLOG_test","getRecordList test: " + it.size)
        //    mapper.mapListDbModelToListEntity(it)
        //}
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

            ////-Log.d("MYLOG_test","id: " + passwordEntity.nonce)
            recordDao.insertRecord(recordEntityMapper.mapEntityToDbModel(passwordEntity))
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
                    is_deleted = false,
                    is_favorite = false,
                    login = passwordEntity.login,
                    name = passwordEntity.name,
                    user_id = passwordEntity.userId
                    //passwordEntity.nonce
                )
            )

            ////-Log.d("MYLOG_test","id: " + passwordEntity.nonce)
            recordDao.deleteRecord(passwordEntity.id)
            recordDao.insertRecord(recordEntityMapper.mapEntityToDbModel(passwordEntity))
        } catch (_: Exception){

        }
    }

    override suspend fun deleteRecord(id: UUID) {
        try {
            serverApi.deleteRecord(
               // token.token,
                id.toString()//passwordEntity.id.toString()

            )
            recordDao.deleteRecord(id)//recordEntityMapper.mapEntityToDbModel(passwordEntity))
        } catch (_: Exception){

        }
    }

}
