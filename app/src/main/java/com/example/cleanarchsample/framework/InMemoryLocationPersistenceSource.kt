package com.example.cleanarchsample.framework

import com.example.datamodule.LocationPersistenceSource
import com.example.domainmodule.Location

class InMemoryLocationPersistenceSource : LocationPersistenceSource {

    private var locations: List<Location> = emptyList()

    override fun getPersistedLocations(): List<Location> = locations

    override fun saveNewLocation(location: Location) {
        locations = locations + location
    }
}