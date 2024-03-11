package com.terra.mobile.retrofit.repository

import kotlinx.coroutines.flow.Flow
import com.terra.mobile.retrofit.Result

interface HeathRepository {
    suspend fun getHealth(): Flow<Result<String>>
}