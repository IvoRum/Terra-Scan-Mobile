package com.terra.mobile.model


data class SoilPointDTO(
    val lon: Double,
    val lat: Double
)

data class SoilResponse(val multipolygon: List<SoilPointDTO>)