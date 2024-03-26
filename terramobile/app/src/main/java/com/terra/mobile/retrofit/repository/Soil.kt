package com.terra.mobile.retrofit.repository

import com.terra.mobile.model.SoilAriaRequest
import com.terra.mobile.model.SoilDTO
import com.terra.mobile.model.SoilPointDTO
import com.terra.mobile.model.SoilResponse
import com.terra.mobile.retrofit.Api

interface SoilRepository {
    suspend fun getTestSoil(token: String): List<SoilDTO>
    suspend fun getSoil(token: String, request: SoilAriaRequest): List<SoilDTO>
}

class NetworkSoilRepository(
    private val api: Api
) : SoilRepository {
    override suspend fun getTestSoil(token: String): List<SoilDTO> = api.getTestSoil(token)
    override suspend fun getSoil(token: String, request: SoilAriaRequest): List<SoilDTO> =
        api.getSoil(token, request)
}

