package com.pdguru.airlinedatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdguru.airlinedatabase.model.AirlineInfo
import com.pdguru.airlinedatabase.networking.AirlineInterface
import com.pdguru.airlinedatabase.networking.RetrofitFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import org.koin.core.component.KoinComponent
import retrofit2.Response

class MainViewModel(moshi: Moshi, client: OkHttpClient) : ViewModel(), KoinComponent {

    private val _state = MutableLiveData<List<AirlineInfo>>()
    val state = _state as LiveData<List<AirlineInfo>>

    private var serverInterface: AirlineInterface =
        RetrofitFactory.createRetrofit(moshi, client, BASE_URL).create(AirlineInterface::class.java)

    fun fetchData() {
        viewModelScope.launch {
            handleResponse(serverInterface.getAirlineInfo())
        }
    }

    private fun handleResponse(response: Response<List<AirlineInfo>>) {
        if (response.isSuccessful){
            _state.postValue(response.body()!!.toList())
        }
    }


}
