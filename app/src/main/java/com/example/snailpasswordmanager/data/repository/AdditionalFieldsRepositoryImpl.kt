package com.example.snailpasswordmanager.data.repository

import android.util.Log
import com.example.snailpasswordmanager.data.database.record.RecordAddFieldDao
import com.example.snailpasswordmanager.data.database.record.UserDao
import com.example.snailpasswordmanager.data.model.RecordAddFieldEntityMapper
import com.example.snailpasswordmanager.data.model.UserEntityMapper
import com.example.snailpasswordmanager.data.retrofit2.Registration
import com.example.snailpasswordmanager.data.retrofit2.ServerApi
import com.example.snailpasswordmanager.data.retrofit2.Token
import com.example.snailpasswordmanager.domain.model.RecordAddFieldEntity
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.AdditionalFieldsRepository
import com.example.snailpasswordmanager.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import okhttp3.Credentials
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import javax.inject.Inject

class AdditionalFieldsRepositoryImpl @Inject constructor(
    var serverApi: ServerApi,
    private val fieldDao: RecordAddFieldDao,
) : AdditionalFieldsRepository {

    private val fieldEntityMapper = RecordAddFieldEntityMapper()
    override suspend fun getField(id:UUID): Flow<List<RecordAddFieldEntity>?> {
        return fieldDao.getFieldsByRecord(id.toString()).map {

            //-Log.d("test",it.size.toString() + " 3 -" + id + " " + id.toString())
            fieldEntityMapper.mapListDbModelToListEntity(it)
        }
    }

    override suspend fun cloneFieldById(id: UUID) {


        try {
            val fields = serverApi.getAdditionalFields(id.toString())//token.token
            fieldDao.deleteFields()

            if (fields != null) {
                //-Log.d("test",fields.size.toString())
                for (field in fields) {
                    //-Log.d("test",field.name + " : " + field.value)
                    fieldDao.addField(fieldEntityMapper.mapEntityToDbModel(field))
                }
            }
        }
        catch (e : Exception){
            //-Log.d("Http",e.toString())
        }
    }

    override suspend fun insertField(addFieldEntity: RecordAddFieldEntity) {

        try {
            serverApi.postAdditionalFields(addFieldEntity)
        }
        catch (e : Exception){
            //-Log.d("Http",e.toString())
        }
    }

    override suspend fun editField(addFieldEntity: RecordAddFieldEntity) {
        try {
            serverApi.editAdditionalFields(addFieldEntity)
            fieldDao.deleteFieldById(addFieldEntity.id.toString())
        }
        catch (e : Exception){
            //-Log.d("Http",e.toString())
        }

    }

    override suspend fun editFieldList(addFieldEntityList: List<RecordAddFieldEntity>) {
        try{
            for(t in addFieldEntityList){
                serverApi.editAdditionalFields(t)
                fieldDao.deleteFieldById(t.id.toString())

            }
        }
        catch (_:Exception){}
    }
}