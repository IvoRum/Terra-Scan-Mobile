package com.terra.mobile.retrofit.repositoryimpl

import com.terra.mobile.model.AuthenticationRequest
import com.terra.mobile.model.AuthenticationResponse
import com.terra.mobile.retrofit.Api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import com.terra.mobile.retrofit.Result
import com.terra.mobile.retrofit.repository.AuthRepository

class AuthRepositoryImpl(private val api: Api): AuthRepository {
    override suspend fun authenticate(authRequst: AuthenticationRequest): Flow<Result<AuthenticationResponse>> {
        return flow {
            val productsFromApi = try {
                api.getAuthList(authRequst)
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
            emit(Result.Success(productsFromApi))
        }
    }
}