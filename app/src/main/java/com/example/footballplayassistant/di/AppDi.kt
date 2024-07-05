package com.example.footballplayassistant.di

import com.example.footballplayassistant.viewmodels.AuthenticationViewModel
import com.example.footballplayassistant.viewmodels.EventsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModel<AuthenticationViewModel>{
        AuthenticationViewModel(
            checkRegistrationCodeUseCase = get(),
            checkRecoveryCodeUseCase = get(),
            checkUserForAuthorizationUseCase = get(),
            checkUserRegistrationStepOneUseCase = get(),
            saveUserToDBUseCase = get(),
            sendCodeToEmailUseCase = get(),
            sendCodeToPhoneUseCase = get(),
            recoveryPasswordUseCase = get())
    }

    viewModel<EventsViewModel>{
        EventsViewModel(
            cancelEventUseCase = get(),
            createEventUseCase = get(),
            eventRegistrationUseCase = get(),
            getActiveEventsUseCase = get(),
            getUserEventsUseCase = get(),
            getEventUseCase = get(),
            getListParticipantsUseCase = get(),
            leaveEventUseCase = get()
        )
    }
}