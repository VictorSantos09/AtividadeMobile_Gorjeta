package com.example.gorjeta.screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GorjetaViewModel : ViewModel(){
    private val valorConta = MutableStateFlow("")
    val totalConta: StateFlow<String> = valorConta

    private val _porcentagemGorjeta = MutableStateFlow(18f)
    val porcentagemGorjeta: StateFlow<Float> = _porcentagemGorjeta

    fun calcularTotalValorFixo(): Float {
        val bill = valorConta.value.toFloatOrNull() ?: 0f
        val fixedTip = calculaValorFixo()
        return bill + fixedTip
    }
    fun calcularTotalValorPersonalizado(): Float{
        val bill = valorConta.value.toFloatOrNull() ?: 0f
        val customTip = calcularGorjetaCustomizada()
        return bill + customTip
    }
    fun alterarConta(amount: String){
        valorConta.value = amount
    }
    fun alterarGorjeta(percentage: Float){
        _porcentagemGorjeta.value = percentage
    }
    fun calcularGorjetaCustomizada(): Float{
        val bill = valorConta.value.toFloatOrNull() ?: 0f
        return bill * (_porcentagemGorjeta.value/100)
    }
    fun calculaValorFixo(): Float{
        val bill = valorConta.value.toFloatOrNull() ?: 0f
        return bill * 0.15f
    }
}