package com.vjezba.domain.repository

import androidx.lifecycle.LiveData
import com.vjezba.domain.model.AllPersons

interface RoomDisplayAllUsersRepository {

    fun getAllPersons() : LiveData<List<AllPersons>>

}