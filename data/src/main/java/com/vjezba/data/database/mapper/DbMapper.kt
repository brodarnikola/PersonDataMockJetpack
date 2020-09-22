package com.vjezba.data.database.mapper

import com.vjezba.data.database.model.AllPersonDb
import com.vjezba.domain.model.*

interface DbMapper {

    fun mapDbAllPersonsToDomainPerson(persons: List<AllPersonDb>): List<AllPersons>
}