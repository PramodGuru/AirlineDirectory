package com.pdguru.airlinedatabase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.pdguru.airlinedatabase.details.AirlineDetails
import com.pdguru.airlinedatabase.model.AirlineInfo
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        val recyclerView = findViewById<RecyclerView>(R.id.rv_listing)
        val onItemClick: (airline: AirlineInfo) -> Unit = {
            startActivity(
                Intent(this, AirlineDetails::class.java)
                    .putExtra(INTENT_EXTRA, it)
            )
        }

        viewModel.fetchData()
        viewModel.state.observe(this) {
            recyclerView.adapter = MainListingAdapter(it, onItemClick)
        }
    }
}
