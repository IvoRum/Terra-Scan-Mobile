package com.terra.mobile.view.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.MapStyleOptions
import com.terra.mobile.map.MapEvent
import com.terra.mobile.map.MapState
import com.terra.mobile.map.MapStyle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MapsViewModel @Inject constructor(): ViewModel() {


    var state by mutableStateOf(MapState())


    init {
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
        when(event) {
            is MapEvent.ToggleFalloutMap -> {
                state = state.copy(
                    properties = state.properties.copy(
                        mapStyleOptions = if(state.isFalloutMap) {
                            null
                        } else MapStyleOptions(MapStyle.json),
                    ),
                    isFalloutMap = !state.isFalloutMap
                )
            }
            is MapEvent.OnMapLongClick -> {
                viewModelScope.launch {
                    /*
                    repository.insertParkingSpot(ParkingSpot(
                        event.latLng.latitude,
                        event.latLng.longitude
                    ))

                     */
                }
            }
            /*
            is MapEvent.OnInfoWindowLongClick -> {
                /*
                viewModelScope.launch {
                    repository.deleteParkingSpot(event.spot)
                }

                 */
            }

             */
        }
    }
}