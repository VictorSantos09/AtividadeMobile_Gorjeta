package com.example.gorjeta.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GorjetaScreen(gorjetaViewModel: GorjetaViewModel = viewModel()) {
    val quantidade by gorjetaViewModel.totalConta.collectAsState()
    val porcentagemGorjeta by gorjetaViewModel.porcentagemGorjeta.collectAsState()

    val totalFixo = gorjetaViewModel.calcularTotalValorFixo()
    val totalCustomizado = gorjetaViewModel.calcularTotalValorPersonalizado()
    val valorCustomizado = gorjetaViewModel.calcularGorjetaCustomizada()
    val valorFixo = gorjetaViewModel.calculaValorFixo()

    var valorAlterado = remember { mutableStateOf(false) }
    var valorDigitado = remember {mutableFloatStateOf(0f)}

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Quantidade")

            OutlinedTextField(
                value = quantidade,
                onValueChange = {
                    gorjetaViewModel.alterarConta(it)

                    val valor = it.toFloatOrNull() ?: 0f
                    valorDigitado.floatValue = valor
                    valorAlterado.value = true},
                modifier = Modifier.weight(1f),
                singleLine = true,
                isError = valorAlterado.value && valorDigitado.floatValue <=0,
                supportingText = {
                    if (valorAlterado.value && valorDigitado.floatValue <= 0) {
                        Text(text = "Informe uma quantidade")
                    }
                }
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Personalizada %",
                fontSize = 18.sp
            )

            Slider(
                value = porcentagemGorjeta,
                onValueChange = { gorjetaViewModel.alterarGorjeta(it) },
                valueRange = 0f..100f,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "15%")

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("R$ ${"%.2f".format(valorFixo)}") },
                )

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = {Text("R$ ${"%.2f".format(totalFixo)}")}
                )

            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "%.0f%%".format(porcentagemGorjeta))

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = {Text("R$ ${"%.2f".format(valorCustomizado)}")}
                )

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = {Text("R$ ${"%.2f".format(totalCustomizado)}")}
                )
            }
        }
    }
}
