package com.pdguru.airlinedatabase.details

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.pdguru.airlinedatabase.FAVS
import timber.log.Timber

class AirlineDetailViewModel(private val sharedPref: SharedPreferences) : ViewModel() {

    fun getFavsAsList(): MutableList<String> {
        val fav = sharedPref.getString(FAVS, null)
            ?.replace("[", "")
            ?.replace("]", "")
            ?.split(",")
        val favList = mutableListOf<String>()
        fav?.forEach { favList.add(it.trim()) }
        return favList
    }

    fun isFav(name: String): Boolean {
        val favList = getFavsAsList()
        return favList.contains(name)
    }

    fun removeFromFavs(name: String) {
        val favList = getFavsAsList()
        favList.remove(name)
        with(sharedPref.edit()) {
            putString(FAVS, "$favList")
            apply()
        }
        Timber.d("delete ${getFavsAsList()}")
    }

    fun addToFavs(name: String) {
        val favList = getFavsAsList()
        favList.add(name)
        Timber.d("Favlist:: $favList")
        with(sharedPref.edit()) {
            putString(FAVS, "$favList")
            apply()
        }
        Timber.d(" add:: ${getFavsAsList()}")
    }
}
