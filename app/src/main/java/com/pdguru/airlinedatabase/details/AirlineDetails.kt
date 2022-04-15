package com.pdguru.airlinedatabase.details

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.pdguru.airlinedatabase.BASE_URL
import com.pdguru.airlinedatabase.INTENT_EXTRA
import com.pdguru.airlinedatabase.R
import com.pdguru.airlinedatabase.model.AirlineInfo
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class AirlineDetails : AppCompatActivity() {
    private lateinit var airline: AirlineInfo
    private val viewModel by viewModel<AirlineDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_airline_details)

        airline = intent.getParcelableExtra(INTENT_EXTRA)!!

        title = airline.name
        findViewById<TextView>(R.id.tv_detail_name).text = "${airline.name} (${airline.code})"
        findViewById<TextView>(R.id.tv_detail_website).text = airline.site
        findViewById<TextView>(R.id.tv_detail_phone).text = airline.phone

        findViewById<TextView>(R.id.tv_detail_alliance).text = airline.alliance?.let {
            when (it.lowercase()) {
                "ow" -> "OneWorld Alliance"
                "sa" -> "Star Alliance"
                "st" -> "SkyTeam Alliance"
                else -> "Not an alliance member"
            }
        }

        val picasso: Picasso = Picasso.get()
        picasso.load(BASE_URL + airline.logoURL)
            .placeholder(R.drawable.ic_flight_32)
            .into(findViewById<ImageView>(R.id.iv_detail_logo))


        val favCheckBox = findViewById<CheckBox>(R.id.cb_detail_fav)
        favCheckBox.isChecked = viewModel.isFav(airline.name!!)
        favCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.addToFavs(airline.name!!)
            } else {
                viewModel.removeFromFavs(airline.name!!)
            }
        }
    }
}
