package com.vjezba.persondatamockjetpack.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vjezba.domain.repository.RoomDisplayAllUsersRepository
import kotlinx.coroutines.launch

class RoomDisplayAllUsersViewModel internal constructor(
    val allPersonsRepo: RoomDisplayAllUsersRepository
) : ViewModel() {
    val allPersons =
        allPersonsRepo.getAllPersons()

    fun deleteSelectedProgrammingLanguage(languagedId: Int) {
        viewModelScope.launch {
            //savedLanguages.deleteSelectedProgrammingLanguage(languagedId)
        }
    }

}
