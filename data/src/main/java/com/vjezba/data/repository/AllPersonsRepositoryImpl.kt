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

package com.vjezba.data.repository

import androidx.lifecycle.map
import androidx.lifecycle.LiveData
import com.vjezba.data.database.dao.AllPersonsDao
import com.vjezba.data.database.dao.AllPhonesDao
import com.vjezba.data.database.mapper.DbMapper
import com.vjezba.data.database.model.AllPersonDb
import com.vjezba.domain.model.AllPersons
import com.vjezba.domain.repository.RoomDisplayAllUsersRepository

/**
 * RepositoryResponseApi module for handling data operations.
 */
class AllPersonsRepositoryImpl  constructor(
    private val allPersons: AllPersonsDao,
    private val allPhones: AllPhonesDao,
    private  val dbMapper: DbMapper)
    : RoomDisplayAllUsersRepository   {

    override fun getAllPersons() : LiveData<List<AllPersons>> {
        return allPersons.getPersons().map {dbMapper.mapDbAllPersonsToDomainPersons(it)}
    }

    override fun getPersonDetails(personId: Int): LiveData<AllPersons> {
        return allPersons.getPersonById(personId).map {dbMapper.mapDbPersonToDomainPerson(it)}
    }

    override suspend fun changeUpdatePersonDetails(personId: Int, name: String, description: String, address: String) : Int {
       return allPersons.updateChangePersonDetails(personId, name, description, address) //.map {dbMapper.mapDbPersonToDomainPerson(it)}
    }

    override suspend fun deleteUser(personId: Int): Int {
        allPhones.deleteAllPhonesForThisPersonId(personId)
        return allPersons.deleteUser(personId)
    }

    override suspend fun findLastUserId(): Long {
        return allPersons.getLastPersonId()
    }

    override suspend fun addNewUser(newUser: AllPersons): Long {
        val newUserDb = AllPersonDb(newUser.personId, newUser.name, newUser.description, newUser.address)
        return allPersons.insertNewUser(newUserDb)
    }

}
