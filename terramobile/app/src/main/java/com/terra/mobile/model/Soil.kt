package com.terra.mobile.model


data class SoilPointDTO(
    val lon: Float,
    val lat: Float
)

data class SoilResponse(val multipolygon: List<SoilPointDTO>)