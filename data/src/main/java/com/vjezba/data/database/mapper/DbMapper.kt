package com.vjezba.data.database.mapper

import com.vjezba.data.database.model.AllPersonDb
import com.vjezba.domain.model.*

interface DbMapper {

    fun mapDbAllPersonsToDomainPersons(persons: List<AllPersonDb>): List<AllPersons>

    fun mapDbPersonToDomainPerson(persons: AllPersonDb): AllPersons
}