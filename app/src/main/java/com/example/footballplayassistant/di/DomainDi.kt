package com.example.footballplayassistant.di

import com.example.domain.usecases.auth.impl.CheckRecoveryCodeUseCaseImpl
import com.example.domain.usecases.auth.impl.CheckRegistrationCodeUseCaseImpl
import com.example.domain.usecases.auth.impl.CheckUserForAuthorizationUseCaseImpl
import com.example.domain.usecases.auth.impl.CheckUserRegistrationStepOneUseCaseImpl
import com.example.domain.usecases.auth.impl.RecoveryPasswordUseCaseImpl
import com.example.domain.usecases.auth.impl.SaveUserToDBUseCaseImpl
import com.example.domain.usecases.auth.impl.SendCodeToEmailUseCaseImpl
import com.example.domain.usecases.auth.impl.SendCodeToPhoneUseCaseImpl
import com.example.domain.usecases.auth.interfaces.CheckRecoveryCodeUseCase
import com.example.domain.usecases.auth.interfaces.CheckRegistrationCodeUseCase
import com.example.domain.usecases.auth.interfaces.CheckUserForAuthorizationUseCase
import com.example.domain.usecases.auth.interfaces.CheckUserRegistrationStepOneUseCase
import com.example.domain.usecases.auth.interfaces.RecoveryPasswordUseCase
import com.example.domain.usecases.auth.interfaces.SaveUserToDBUseCase
import com.example.domain.usecases.auth.interfaces.SendCodeToEmailUseCase
import com.example.domain.usecases.auth.interfaces.SendCodeToPhoneUseCase
import com.example.domain.usecases.events.impl.CancelEventUseCaseImpl
import com.example.domain.usecases.events.impl.CreateEventUseCaseImpl
import com.example.domain.usecases.events.impl.EventRegistrationUseCaseImpl
import com.example.domain.usecases.events.impl.GetActiveEventsUseCaseImpl
import com.example.domain.usecases.events.impl.GetEventUseCaseImpl
import com.example.domain.usecases.events.impl.GetListParticipantsUseCaseImpl
import com.example.domain.usecases.events.impl.GetUserEventsUseCaseImpl
import com.example.domain.usecases.events.impl.LeaveEventUseCaseImpl
import com.example.domain.usecases.events.interfaces.CancelEventUseCase
import com.example.domain.usecases.events.interfaces.CreateEventUseCase
import com.example.domain.usecases.events.interfaces.EventRegistrationUseCase
import com.example.domain.usecases.events.interfaces.GetActiveEventsUseCase
import com.example.domain.usecases.events.interfaces.GetEventUseCase
import com.example.domain.usecases.events.interfaces.GetListParticipantsUseCase
import com.example.domain.usecases.events.interfaces.GetUserEventsUseCase
import com.example.domain.usecases.events.interfaces.LeaveEventUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<CheckRegistrationCodeUseCase>{
        CheckRegistrationCodeUseCaseImpl(authenticationRepository = get())
    }

    factory<CheckRecoveryCodeUseCase>{
        CheckRecoveryCodeUseCaseImpl(authenticationRepository = get())
    }

    factory<CheckUserForAuthorizationUseCase>{
        CheckUserForAuthorizationUseCaseImpl(authenticationRepository = get())
    }

    factory<CheckUserRegistrationStepOneUseCase>{
        CheckUserRegistrationStepOneUseCaseImpl(authenticationRepository = get())
    }

    factory<SaveUserToDBUseCase>{
        SaveUserToDBUseCaseImpl(authenticationRepository = get())
    }

    factory<SendCodeToEmailUseCase>{
        SendCodeToEmailUseCaseImpl(authenticationRepository = get())
    }

    factory<SendCodeToPhoneUseCase>{
        SendCodeToPhoneUseCaseImpl(authenticationRepository = get())
    }

    factory<RecoveryPasswordUseCase> {
        RecoveryPasswordUseCaseImpl(authenticationRepository = get())
    }

    factory<CancelEventUseCase> {
        CancelEventUseCaseImpl(eventsRepository = get())
    }

    factory<CreateEventUseCase> {
        CreateEventUseCaseImpl(eventsRepository = get())
    }

    factory<EventRegistrationUseCase> {
        EventRegistrationUseCaseImpl(eventsRepository = get())
    }

    factory<GetActiveEventsUseCase> {
        GetActiveEventsUseCaseImpl(eventsRepository = get())
    }

    factory<GetEventUseCase> {
        GetEventUseCaseImpl(eventsRepository = get())
    }

    factory<GetListParticipantsUseCase> {
        GetListParticipantsUseCaseImpl(eventsRepository = get())
    }

    factory<GetUserEventsUseCase> {
        GetUserEventsUseCaseImpl(eventsRepository = get())
    }

    factory<LeaveEventUseCase> {
        LeaveEventUseCaseImpl(eventsRepository = get())
    }

}