package com.terra.mobile.model

import kotlinx.serialization.Serializable

@Serializable
data class SoilPointDTO(
    val lon: Double,
    val lat: Double
)

@Serializable
data class SoilResponse(val multipolygon: List<SoilPointDTO>)

data class SoilAriaRequest(val latitude: Double, val longitude: Double, val zoom: Double)

data class PolygonPoint(val lon:Double,val lat:Double)

data class SoilDTO(val coordinates:List<PolygonPoint>, val soilNumber: Int, val soilType:String)