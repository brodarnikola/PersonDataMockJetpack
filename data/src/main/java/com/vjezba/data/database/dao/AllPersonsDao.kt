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

package com.vjezba.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vjezba.data.database.model.AllPersonDb

/**
 * The Data Access Object for the Plant class.
 */
@Dao
interface AllPersonsDao {
    @Query("SELECT * FROM persons ORDER BY id")
    fun getPersons(): LiveData<List<AllPersonDb>>

    @Query("SELECT * FROM persons WHERE id = :personId")
    fun getPersonById(personId: Int): LiveData<AllPersonDb>

    @Query("UPDATE persons SET name = :name, description = :description, address = :address  WHERE id = :personId")
    suspend fun updateChangePersonDetails(personId: Int, name: String, description: String, address: String) : Int

    @Query("DELETE FROM persons WHERE id = :personId")
    suspend fun deleteUser(personId: Int) : Int


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<AllPersonDb>)


    @Query("SELECT id FROM persons ORDER BY id DESC LIMIT 1")
    fun getLastPersonId(): Long

    @Insert
    suspend fun insertNewUser(user: AllPersonDb): Long
}
