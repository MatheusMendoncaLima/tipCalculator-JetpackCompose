package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalculatorTheme {
                var textBoxValue by remember { mutableStateOf("") }
                var tip by remember { mutableStateOf(0f) }
                MainScreen(textBoxValue, {
                    var formattedIt=it.replace(",", ".")
                    textBoxValue=formattedIt
                    if(formattedIt.toFloatOrNull() != null){
                        tip = (formattedIt.toFloat())*0.1f;
                    }
                                         }, tip)
            }
        }
    }
}

@Composable
fun MainScreen(textBoxValue : String = "",
               textBoxOnChange: (String) -> Unit = {},
               tip : Float = 0.00f
               ){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column (horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            val heightDp = LocalConfiguration.current.screenHeightDp.dp/2 - 150.dp
            Box(modifier = Modifier.fillMaxWidth()
                .height(heightDp),
                contentAlignment = Alignment.BottomCenter) {
                Text(
                    text = "Calculadora de gorjeta 5000",
                    style = MaterialTheme.typography.titleLarge,
                )

            }
            TextField(
                value = textBoxValue,
                placeholder = {
                    Text(
                        text = "Insira o valor da conta"
                    )
                },
                onValueChange = textBoxOnChange,
                modifier = Modifier.padding(top = 100.dp)
            )
            Text(
                text = "o valor da gorjeta é R$$tip".replace(".", ","),
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
// widthDp =  ((1080/ 3.5) as Int)
// heightDp = 2400 / 3.5
@Preview(showBackground = true,
    showSystemUi = true,
    device = "id:pixel_7"
)
@Composable
fun GreetingPreview() {

    TipCalculatorTheme {
        MainScreen()
    }
}