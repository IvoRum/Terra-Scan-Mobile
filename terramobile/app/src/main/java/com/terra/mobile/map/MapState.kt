package com.terra.mobile.map

import com.google.maps.android.compose.MapProperties

data class MapState(
    val properties: MapProperties = MapProperties(),
    //val parkingSpots: List<ParkingSpot> = emptyList(),
    val isFalloutMap: Boolean = false
)