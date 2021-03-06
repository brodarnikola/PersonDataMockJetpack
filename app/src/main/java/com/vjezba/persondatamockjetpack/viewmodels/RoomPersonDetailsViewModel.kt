/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vjezba.persondatamockjetpack.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vjezba.domain.model.AllPersons
import com.vjezba.domain.repository.RoomDisplayAllUsersRepository
import kotlinx.coroutines.launch


class RoomPersonDetailsViewModel(
    val roomDisplayAllUsersRepository: RoomDisplayAllUsersRepository,
    personId: Int
) : ViewModel() {

    //val isSavedLanguage = savedLanguagesRepository.isLanguageSaved(languagesId)
    val personDetails = roomDisplayAllUsersRepository.getPersonDetails(personId)

    suspend fun updateChangeUserDetails(personId: Int, name: String, description: String, address: String ) : Int {
        return roomDisplayAllUsersRepository.changeUpdatePersonDetails(personId, name, description, address)
    }

    suspend fun deleteUser(personId: Int) : Int {
        return roomDisplayAllUsersRepository.deleteUser(personId)
    }

}
