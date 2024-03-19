package com.terra.mobile.map

import com.google.maps.android.compose.MapProperties
import com.terra.mobile.model.SoilResponse
import kotlinx.coroutines.flow.MutableStateFlow

data class MapState(
    val properties: MapProperties = MapProperties(),
    //val parkingSpots: List<ParkingSpot> = emptyList(),
    val isFalloutMap: Boolean = false,
    var _soil : SoilResponse= SoilResponse(emptyList())
)