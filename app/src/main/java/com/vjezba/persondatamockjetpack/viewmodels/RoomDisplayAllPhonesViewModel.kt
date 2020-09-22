package com.vjezba.persondatamockjetpack.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vjezba.domain.model.AllPersons
import com.vjezba.domain.model.AllPhones
import com.vjezba.domain.repository.RoomDisplayAllPhonesRepository
import com.vjezba.domain.repository.RoomDisplayAllUsersRepository
import kotlinx.coroutines.launch

class RoomDisplayAllPhonesViewModel internal constructor(
    val allPhonesRepo: RoomDisplayAllPhonesRepository,
    personId: Int
) : ViewModel() {

    val allPhones =
        allPhonesRepo.getAllPhones(personId)

    suspend fun addNewPhone(  name: String, operater: String, userId: Int ) : Long {
        val newUser = AllPhones(0 , name, operater, userId)
        return allPhonesRepo.addNewPhone(newUser)
    }

    fun deleteSelectedPhone(phone: AllPhones) {
        viewModelScope.launch {
            allPhonesRepo.deletePhone(phone)
        }
    }

}
