package com.pdguru.airlinedatabase.networking

import com.pdguru.airlinedatabase.model.AirlineInfo
import retrofit2.Response
import retrofit2.http.GET

interface AirlineInterface {
    @GET("h/mobileapis/directory/airlines")
    suspend fun getAirlineInfo(): Response<List<AirlineInfo>>
}
