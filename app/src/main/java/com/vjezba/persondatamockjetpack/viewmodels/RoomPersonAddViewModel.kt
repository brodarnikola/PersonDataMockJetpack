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


class RoomPersonAddViewModel(
    val roomDisplayAllUsersRepository: RoomDisplayAllUsersRepository
) : ViewModel() {

    suspend fun findLastUserId() : Long {
        return roomDisplayAllUsersRepository.findLastUserId()
    }

    suspend fun addNewUser(lastPersonID: Int, name: String, description: String, address: String ) : Long {
        val finalLastPersonID = lastPersonID + 1
        val newUser = AllPersons(finalLastPersonID, name, description, address)
        return roomDisplayAllUsersRepository.addNewUser(newUser)
    }


   /* suspend fun updateChangeUserDetails(personId: Int, name: String, description: String, address: String ) : Int {
        return roomDisplayAllUsersRepository.changeUpdatePersonDetails(personId, name, description, address)
    }

    suspend fun deleteUser(personId: Int) : Int {
        return roomDisplayAllUsersRepository.deleteUser(personId)
    }*/

}
