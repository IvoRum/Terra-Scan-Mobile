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
import com.terra.mobile.model.AuthenticationRequest
import com.terra.mobile.model.AuthenticationResponse
import com.terra.mobile.model.SoilResponse
import com.terra.mobile.retrofit.Result
import com.terra.mobile.retrofit.repository.AuthRepository
import com.terra.mobile.retrofit.repository.SoilRepository
import com.terra.mobile.retrofit.repositoryimpl.SoilRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MapsViewModel @Inject constructor(private val soilRepository: SoilRepositoryImpl) : ViewModel() {

    private val _soil = MutableStateFlow<SoilResponse>(SoilResponse(emptyList()))
    val soil = _soil.asStateFlow().value.multipolygon

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()


    var state by mutableStateOf(MapState())


    init {
        //Set up test soil
        viewModelScope.launch {
            AuthenticationResponse("")
            soilRepository.getTestSoil()
                .collectLatest { result ->
                    when (result) {
                        is Result.Error -> {
                            _showErrorToastChannel.send(true)
                        }

                        is Result.Success -> {
                            result.data?.let { products ->
                                _soil.update { products }
                            }
                        }
                    }
                }
        }
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