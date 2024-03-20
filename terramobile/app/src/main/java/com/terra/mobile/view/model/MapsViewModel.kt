package com.terra.mobile.view.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.android.gms.maps.model.MapStyleOptions
import com.terra.mobile.AppActivity
import com.terra.mobile.map.MapEvent
import com.terra.mobile.map.MapState
import com.terra.mobile.map.MapStyle
import com.terra.mobile.retrofit.repository.SoilRepository
import kotlinx.coroutines.launch


class MapsViewModel constructor(private val soilRepository: SoilRepository) : ViewModel() {

    var state by mutableStateOf(MapState(_soil = emptyList()))


    init {
        getBgSoil()
        viewModelScope.launch {
            /*
            repository.getParkingSpots().collectLatest { spots ->
                state = state.copy(
                    parkingSpots = spots
                )
            }
             */
        }
    }


    fun onEvent(event: MapEvent) {
        when (event) {
            is MapEvent.ToggleFalloutMap -> {
                state = state.copy(
                    properties = state.properties.copy(
                        mapStyleOptions = if (state.isFalloutMap) {
                            null
                        } else MapStyleOptions(MapStyle.json),
                    ), isFalloutMap = !state.isFalloutMap
                )
            }

            is MapEvent.OnMapLongClick -> {
            }
        }
    }

    fun getBgSoil() {
        viewModelScope.launch {
            state._soil = soilRepository.getTestSoil()
        }
    }

    /**
     * Factory for [MarsViewModel] that takes [MarsPhotosRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AppActivity)
                val soilRope = application.container.soilRepository
                MapsViewModel( soilRope)
            }
        }
    }
}