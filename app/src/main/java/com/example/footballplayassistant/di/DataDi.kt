package com.example.footballplayassistant.di

import com.example.data.client.httpClientAndroid
import com.example.data.repository.AuthenticationRepositoryImpl
import com.example.data.repository.EventsRepositoryImpl
import com.example.data.services.ApiServiceAuthentication
import com.example.data.services.ApiServiceAuthenticationImpl
import com.example.data.services.ApiServiceEvents
import com.example.data.services.ApiServiceEventsImpl
import com.example.domain.repositories.AuthenticationRepository
import com.example.domain.repositories.EventsRepository
import io.ktor.client.HttpClient
import org.koin.dsl.module

val dataModule = module {
    single<AuthenticationRepository>{
        AuthenticationRepositoryImpl(apiService = get())
    }
    single<ApiServiceAuthentication>{
        ApiServiceAuthenticationImpl(client = get())
    }
    single {
        provideHttpClient()
    }
    single<EventsRepository>{
        EventsRepositoryImpl(apiServiceEvents = get())
    }
    single<ApiServiceEvents>{
        ApiServiceEventsImpl(client = get())
    }
}

fun provideHttpClient(): HttpClient {
    return httpClientAndroid
}



