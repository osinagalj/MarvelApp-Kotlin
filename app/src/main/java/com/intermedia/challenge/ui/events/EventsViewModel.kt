package com.intermedia.challenge.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intermedia.challenge.data.models.Event
import com.intermedia.challenge.data.models.NetResult
import com.intermedia.challenge.data.repositories.EventsRepository
import kotlinx.coroutines.launch


class EventsViewModel(private val eventsRepository: EventsRepository) : ViewModel() {
    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> get() = _events

    init {
        System.out.println("Entro 1")
        loadEvents(0)
    }

    private fun loadEvents(offset: Int) {
        viewModelScope.launch {
            when (val response = eventsRepository.getEvents()) {
                is NetResult.Success -> {

                    val list_ev :MutableList<Event> = ArrayList()

                    /**
                    The best thing would be to obtainthe json objects where the start is not null,
                    since in this way if I bring 25 events and 3 have null start, then I show only 22
                     */
                    //Filter events with null start
                    response.data.eventList.events.forEach {
                        if(it.start != null){
                            list_ev.add(it)
                        }else{
                            System.out.println("HAY UN NULLO XDD")
                        }

                    }

                    // Inserting events only with start date
                    _events.postValue(list_ev)

                }
                is NetResult.Error -> {
                    System.out.println("Error gettin the data from the API using the service >> getEvents() <<")
                }
            }
        }
    }

}