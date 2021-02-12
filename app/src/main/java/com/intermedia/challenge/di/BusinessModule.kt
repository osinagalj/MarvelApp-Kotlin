package com.intermedia.challenge.di

import com.intermedia.challenge.ui.characters.CharactersViewModel
import com.intermedia.challenge.data.repositories.CharactersRepository
import com.intermedia.challenge.data.repositories.EventsRepository
import com.intermedia.challenge.ui.characterdDetails.CharacterDetailsViewModel
import com.intermedia.challenge.ui.events.EventsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val businessModule = module {

    viewModel { CharactersViewModel(get()) }

    single { CharactersRepository(get()) }

    viewModel { EventsViewModel(get()) }

    single { EventsRepository(get()) }

    viewModel { CharacterDetailsViewModel(get()) }

}