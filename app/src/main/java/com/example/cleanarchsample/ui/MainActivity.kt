package com.example.cleanarchsample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cleanarchsample.framework.FakeLocationSource
import com.example.cleanarchsample.framework.InMemoryLocationPersistenceSource
import com.example.cleanarchsample.R
import com.example.datamodule.LocationsRepository
import com.example.usecases.GetLocations
import com.example.usecases.RequestNewLocation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private val locationsAdapter = LocationsAdapter()
    private val presenter: MainPresenter

    init {
        // This would be done by a dependency injector in a complex App
        //
        val persistence = InMemoryLocationPersistenceSource()
        val deviceLocation = FakeLocationSource()
        val locationsRepository = LocationsRepository(persistence, deviceLocation)
        presenter = MainPresenter(
            this,
            GetLocations(locationsRepository),
            RequestNewLocation(locationsRepository)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.adapter = locationsAdapter

        newLocationBtn.setOnClickListener { presenter.newLocationClicked() }

        presenter.onCreate()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun renderLocations(locations: List<Location>) {
        locationsAdapter.items = locations
    }
}