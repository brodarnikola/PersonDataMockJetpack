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

    suspend fun addNewPhone( /*lastPersonID: Int,*/ name: String, operater: String, userId: Int /*, address: String*/ ) : Long {
        //val finalLastPersonID = lastPersonID + 1
        val newUser = AllPhones(name, operater, userId)
        return allPhonesRepo.addNewPhone(newUser)
    }

    fun deleteSelectedProgrammingLanguage(languagedId: Int) {
        viewModelScope.launch {
            //savedLanguages.deleteSelectedProgrammingLanguage(languagedId)
        }
    }

}
