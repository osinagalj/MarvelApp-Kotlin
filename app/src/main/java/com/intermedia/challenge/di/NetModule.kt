package com.intermedia.challenge.di

import com.intermedia.challenge.BuildConfig
import com.intermedia.challenge.data.services.CharacterService
import com.intermedia.challenge.data.services.EventsService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single { provideHttpClient() }
    single { provideRetrofit(get()) }

    single { provideCharacterService(get()) }

    single { provideEventsService(get()) }


}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}

fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

fun provideCharacterService(retrofit: Retrofit): CharacterService =
    retrofit.create(CharacterService::class.java)

fun provideEventsService(retrofit: Retrofit): EventsService =
    retrofit.create(EventsService::class.java)
