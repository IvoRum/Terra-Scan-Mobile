package com.terra.mobile.retrofit.repositoryimpl

import android.util.Log
import com.terra.mobile.model.SoilResponse
import com.terra.mobile.retrofit.Api
import com.terra.mobile.retrofit.Result
import com.terra.mobile.retrofit.repository.SoilRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
/*
class SoilRepositoryImpl(private val api: Api) : SoilRepository {
    override suspend fun getTestSoil(): Flow<SoilResponse> {
        return flow {
            var itemFoound = SoilResponse(emptyList())
            api.getTestSoil()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ item -> itemFoound = item },
                    { error -> Log.e("SoilApiError", "geeting bulgarian soils error") })
            emit(itemFoound)
        }

    }
}

 */