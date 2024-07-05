package com.example.footballplayassistant.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.CommonAnswer
import com.example.domain.models.Result
import com.example.domain.models.events.Event
import com.example.domain.usecases.events.interfaces.CancelEventUseCase
import com.example.domain.usecases.events.interfaces.CreateEventUseCase
import com.example.domain.usecases.events.interfaces.EventRegistrationUseCase
import com.example.domain.usecases.events.interfaces.GetActiveEventsUseCase
import com.example.domain.usecases.events.interfaces.GetEventUseCase
import com.example.domain.usecases.events.interfaces.GetListParticipantsUseCase
import com.example.domain.usecases.events.interfaces.GetUserEventsUseCase
import com.example.domain.usecases.events.interfaces.LeaveEventUseCase
import kotlinx.coroutines.launch

class EventsViewModel(
    private val cancelEventUseCase: CancelEventUseCase,
    private val createEventUseCase: CreateEventUseCase,
    private val eventRegistrationUseCase: EventRegistrationUseCase,
    private val getActiveEventsUseCase: GetActiveEventsUseCase,
    private val getEventUseCase: GetEventUseCase,
    private val getListParticipantsUseCase: GetListParticipantsUseCase,
    private val getUserEventsUseCase: GetUserEventsUseCase,
    private val leaveEventUseCase: LeaveEventUseCase
): ViewModel() {

    fun getEvent(id: Int) {
        val res = getEventUseCase.execute(id = id)
        viewModelScope.launch {
            res.collect {
                when (it) {
                    is Result.Success<*> -> {
                        val saveIt = it.copy()
                        val answer = saveIt.value as Event
                        Log.d("MyLog", answer.message + "\n" + answer.data)
                    }
                    is Result.ErrorNetwork -> {
                        Log.d("MyLog", "ErrorNetwork ${it.value}")
                    }
                    else -> { Log.d("MyLog", "server error $it") }
                }
            }
        }
    }
}