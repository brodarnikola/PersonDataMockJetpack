package com.vjezba.data.di

import com.vjezba.data.database.mapper.DbMapper
import com.vjezba.data.database.mapper.DbMapperImpl
import com.vjezba.data.repository.AllPersonsRepositoryImpl
import com.vjezba.data.repository.AllPhonesRepositoryImpl
import com.vjezba.domain.repository.RoomDisplayAllPhonesRepository
import com.vjezba.domain.repository.RoomDisplayAllUsersRepository
import org.koin.dsl.module


val repositoryModule = module {

  factory<DbMapper> { DbMapperImpl() }
  factory<RoomDisplayAllUsersRepository> { AllPersonsRepositoryImpl(get(), get(), get()) }
  factory<RoomDisplayAllPhonesRepository> { AllPhonesRepositoryImpl(get(), get()) }

  /*factory<DbMapper> { DbMapperImpl() }
  factory<LanguagesRepository> { AllPersonsRepositoryImpl(get(), get()) }
  factory<SavedLanguagesRepository> { SavedLanguagesRepositoryImpl(get(), get()) }
  factory<GithubRepository> { GithubRepositoryImpl(get(), get()) }*/
}