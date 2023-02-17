package com.example.cupcake.model

import androidx.lifecycle.ViewModel
import com.example.cupcake.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val PRICE_PER_CUPCAKE = 2

class OrderViewModel : ViewModel(){

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    fun setQuantity(numberCupcakes: Int){
        _uiState.update { currentState ->
            currentState.copy(
                quantity = numberCupcakes,
                price = numberCupcakes* PRICE_PER_CUPCAKE
            )
        }
    }

    fun setFlavour(desiredFlavour: String){
        _uiState.update { currentState ->
            currentState.copy(
                flavour = desiredFlavour
            )
        }
    }

    fun resetOrder(){
        _uiState.value = OrderUiState()
    }

}