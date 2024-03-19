package com.terra.mobile.view.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terra.mobile.retrofit.repositoryimpl.HeathRepositoryImpl
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.terra.mobile.retrofit.Result

class HealthViewModel( private val healthRepo: HeathRepositoryImpl):ViewModel() {

    private val _products = MutableStateFlow<String>("")
    val products = _products.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            healthRepo.getHealth().collectLatest { result ->
                when(result) {
                    is Result.Error -> {
                        _showErrorToastChannel.send(true)
                    }
                    is Result.Success -> {
                        result.data?.let { products ->
                            Log.w("products", products);
                            _products.update { products }
                        }
                    }
                }
            }
        }
    }
}