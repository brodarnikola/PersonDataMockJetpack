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

package com.vjezba.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class AllPersonDb(
    @PrimaryKey @ColumnInfo(name = "id") val personId: Int,
    var name: String,
    var description: String,
    var address: String = ""
) {

    constructor(name: String, description: String, address: String) : this( 0, name, description, address) {
        this.name = name
        this.description = description
        this.address = address
    }

    override fun toString() = name
}
