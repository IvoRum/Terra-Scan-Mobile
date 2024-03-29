package com.terra.mobile.composable

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.terra.mobile.data.UserState
import com.terra.mobile.map.MapEvent
import com.terra.mobile.model.SoilAriaRequest
import com.terra.mobile.view.model.MapsViewModel
import com.terra.mobile.view.model.UserViewModel

val mapfill: Color = Color(245, 189, 133, 136)
val mapLine: Color = Color(0, 0, 0, 89)

@Composable
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun MapScreen(
    mapViewModel: MapsViewModel = viewModel(),
    userModel: UserViewModel
) {
    val scaffoldState = remember { SnackbarHostState() }
    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }
    val singapore = LatLng(40.79,-73.18)//БГ: 42.7339, 25.4858)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 8f)
    }
    //mapViewModel.getBgSoil((userModel.userUiState as UserState.Success).getTokken())
    Scaffold(
        //scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                mapViewModel.onEvent(MapEvent.ToggleFalloutMap)
            }) {
                Icon(
                    imageVector = if (mapViewModel.state.isFalloutMap) {
                        Icons.Default.ToggleOff
                    } else Icons.Default.ToggleOn,
                    contentDescription = "Toggle Fallout map"
                )
                Text(text = cameraPositionState.position.target.latitude.toString())
                /*
                val soilAria= remember {
                    SoilAriaRequest(cameraPositionState.position.target.latitude,cameraPositionState.position.target.longitude,cameraPositionState.position.zoom as Double)
                }
                mapViewModel.getSoil((userModel.userUiState as UserState.Success).getTokken(),soilAria)

                 */
            }
        }
    ) {
        //val singapore = LatLng(-23.684, 133.903)//Avstraliq
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = mapViewModel.state.properties,
            uiSettings = uiSettings,
            onMapLongClick = {
                //TODO Think of what to a long click will do
                mapViewModel.onEvent(MapEvent.OnMapLongClick(it))
            },
            cameraPositionState = cameraPositionState
        ) {

            val polyline1 = listOf(
                LatLng(-35.016, 143.321),
                LatLng(-34.747, 145.592),
                LatLng(-34.364, 147.891)
            )
            if (!cameraPositionState.isMoving) {
                Polygon(points = polyline1)
                if(!mapViewModel.state._soil.isEmpty()) {
                    var poligons = mapViewModel.state._soil
                    val listOfListOfLatLng: List<List<LatLng>> = poligons.map { soilDTO ->
                        soilDTO.coordinates.map { polygonPoint ->
                            LatLng(polygonPoint.lat, polygonPoint.lon)

                        }
                    }
                    Log.w("Polygons",listOfListOfLatLng.toString())
                    //Polyline(testSoil)
                    listOfListOfLatLng.forEach { poligon->  Polygon(points = poligon, fillColor = mapfill, strokeColor = mapLine)}
                }
            }
        }
    }
}