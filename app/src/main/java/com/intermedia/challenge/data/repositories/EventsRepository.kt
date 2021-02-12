package com.intermedia.challenge.data.repositories

import com.intermedia.challenge.data.models.NetResult
import com.intermedia.challenge.data.services.EventsResponse
import com.intermedia.challenge.data.services.EventsService
import java.util.*

class EventsRepository(private val eventsService: EventsService
): BaseRepository() {

    /**
     * I bring 32 events because the first 7 are null, and since then I eliminate
     * the nulls in the view, less than 25 are shown
     *
    It's hardcoded but I haven't found the correct way yet
     */
    suspend fun getEvents(limit : Int = 32): NetResult<EventsResponse> =
        handleResult(eventsService.getEvents(authParams.getMap(),limit))
}
