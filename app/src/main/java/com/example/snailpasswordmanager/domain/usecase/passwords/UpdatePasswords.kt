package com.example.snailpasswordmanager.domain.usecase.passwords

import android.util.Log
import com.example.snailpasswordmanager.domain.model.InvalidRecordException
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.repository.RecordListRepository
import com.example.snailpasswordmanager.data.retrofit2.ServerApi
import okhttp3.Credentials
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Inject


class UpdatePasswords @Inject constructor(
    private val passwordListRepository: RecordListRepository,
    var serverApi: ServerApi
) {

    @Throws(InvalidRecordException::class)
    suspend operator fun invoke(){

        try {
            val t = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ServerApi::class.java)
            //val a = t.getRecords(credentials)



            //passwordListRepository.insertRecord(passwordEntity)

        }catch (e: IOException){
            Log.d("MYLOG_test","Internet error")
        }catch (e: HttpException){
            Log.d("MYLOG_test","Server error")
        }

    }
}
