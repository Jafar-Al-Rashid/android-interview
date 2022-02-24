package io.mx51.androidinterview.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.mx51.androidinterview.data.model.WeatherDetails
import io.mx51.androidinterview.domain.GetWeatherDetailsUseCase
import io.mx51.androidinterview.domain.WeatherRequestParameters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherDetailsUseCase: GetWeatherDetailsUseCase
): ViewModel() {

    private val _weatherDetails: MutableStateFlow<WeatherDetails?> = MutableStateFlow(null)
    val weatherDetails = _weatherDetails.asStateFlow()

    fun getWeatherDetails(
        location: String = "New York",
        units: String = "m"
    ) {
        viewModelScope.launch {
            _weatherDetails.value = getWeatherDetailsUseCase {
                WeatherRequestParameters(
                    location = location,
                    units = units
                )
            }
        }
    }
}