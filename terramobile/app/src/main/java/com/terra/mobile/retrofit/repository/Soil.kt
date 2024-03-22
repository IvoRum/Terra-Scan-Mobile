package com.terra.mobile.retrofit.repository

import com.terra.mobile.model.SoilPointDTO
import com.terra.mobile.model.SoilResponse
import com.terra.mobile.retrofit.Api

interface SoilRepository {
    suspend fun getTestSoil(token:String): List<SoilPointDTO>
}
class NetworkSoilRepository(
    private val api: Api
) : SoilRepository {
    override suspend fun getTestSoil(token:String): List<SoilPointDTO> =api.getTestSoil(token)
}

