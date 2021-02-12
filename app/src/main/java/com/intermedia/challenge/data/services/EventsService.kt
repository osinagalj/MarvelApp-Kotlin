package com.intermedia.challenge.data.services

import com.google.gson.annotations.SerializedName

import com.intermedia.challenge.data.models.Event
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import java.util.*
import kotlin.collections.HashMap

interface EventsService {

    @GET("events?orderBy=startDate")
    suspend fun getEvents(
        @QueryMap auth: HashMap<String, String>,
        @Query("limit") limit: Int
    ): Response<EventsResponse>
}

data class EventsResponse(
    val code: Int = 0,
    @SerializedName("data")
    val eventList: EventsList
)

data class EventsList(
    @SerializedName("results")
    val events: List<Event>
)