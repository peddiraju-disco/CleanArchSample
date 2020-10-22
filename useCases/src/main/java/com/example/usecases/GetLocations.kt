package com.example.usecases

import com.example.datamodule.LocationsRepository
import com.example.domainmodule.Location


class GetLocations(private val locationsRepository: LocationsRepository) {

    operator fun invoke(): List<Location> = locationsRepository.getSavedLocations()

}
