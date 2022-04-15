package com.pdguru.airlinedatabase.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
@JsonClass(generateAdapter = true)
data class AirlineInfo(

	@Json(name="site")
	val site: String? = null,

	@Json(name="code")
	val code: String? = null,

	@Json(name="alliance")
	val alliance: String? = null,

	@Json(name="phone")
	val phone: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="usName")
	val usName: String? = null,

	@Json(name="__clazz")
	val clazz: String? = null,

	@Json(name="defaultName")
	val defaultName: String? = null,

	@Json(name="logoURL")
	val logoURL: String? = null
) : Parcelable
