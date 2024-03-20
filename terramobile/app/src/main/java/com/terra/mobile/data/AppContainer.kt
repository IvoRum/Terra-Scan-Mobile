/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.terra.mobile.data

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.terra.mobile.retrofit.Api
import com.terra.mobile.retrofit.repository.NetworkSoilRepository
import com.terra.mobile.retrofit.repository.SoilRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.converter.gson.GsonConverterFactory


interface AppContainer {
    val soilRepository: SoilRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "http://192.168.0.105:8081/"

    private val retrofit: Retrofit = Retrofit.Builder()
        // .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitService: Api by lazy {
        retrofit.create(Api::class.java)
    }

    override val soilRepository: SoilRepository by lazy {
        NetworkSoilRepository(retrofitService)
    }
}


