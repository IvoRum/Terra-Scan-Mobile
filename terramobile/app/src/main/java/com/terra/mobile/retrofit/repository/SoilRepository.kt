package com.terra.mobile.retrofit.repository

import com.terra.mobile.model.SoilResponse
import com.terra.mobile.retrofit.Result
import kotlinx.coroutines.flow.Flow

interface SoilRepository {
    suspend fun getTestSoil(): Flow<Result<SoilResponse>>
}