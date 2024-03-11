package com.terra.mobile.retrofit.repositoryimpl

import android.util.Log
import com.terra.mobile.retrofit.Api
import com.terra.mobile.retrofit.repository.HeathRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import com.terra.mobile.retrofit.Result
class HeathRepositoryImpl(private val api: Api) : HeathRepository {
    override suspend fun getHealth(): Flow<Result<String>> {
        return flow {
            val productsFromApi = try {
                api.getProductsList()

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
            Log.w("productsFromApi", productsFromApi);
            emit(Result.Success(productsFromApi))
        }

    }

}