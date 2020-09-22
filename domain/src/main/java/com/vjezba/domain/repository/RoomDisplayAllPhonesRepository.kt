package com.vjezba.domain.repository

import androidx.lifecycle.LiveData
import com.vjezba.domain.model.AllPhones

interface RoomDisplayAllPhonesRepository {

    fun getAllPhones(personId: Int) : LiveData<List<AllPhones>>

    suspend fun addNewPhone(newPhone: AllPhones) : Long

    /*fun getPersonDetails(personId: Int) : LiveData<AllPersons>

    suspend fun changeUpdatePersonDetails(personId: Int, name: String, description: String, address: String) : Int

    suspend fun deleteUser(personId: Int) : Int


    suspend fun findLastUserId() : Long

    suspend fun addNewUser(newUser: AllPersons) : Long*/

}