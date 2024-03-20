package com.terra.mobile.model

import kotlinx.serialization.Serializable

@Serializable
data class SoilPointDTO(
    val lon: Double,
    val lat: Double
)
@Serializable
data class SoilResponse(val multipolygon: List<SoilPointDTO>)