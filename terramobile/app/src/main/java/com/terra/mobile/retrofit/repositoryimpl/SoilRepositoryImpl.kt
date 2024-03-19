package com.terra.mobile.retrofit.repositoryimpl

import com.terra.mobile.model.SoilResponse
import com.terra.mobile.retrofit.Api
import com.terra.mobile.retrofit.Result
import com.terra.mobile.retrofit.repository.SoilRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class SoilRepositoryImpl(private val api: Api): SoilRepository {
    override suspend fun getTestSoil(): Flow<Result<SoilResponse>> {
        return flow {
            val soilFromApi = try {
                api.getTestSoil()

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading products"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading products"))
                return@flow
            }  catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading products"))
                return@flow
            }
            emit(Result.Success(soilFromApi))
        }
    }
}