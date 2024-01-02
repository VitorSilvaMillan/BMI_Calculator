package com.example.bmi_calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bmi_calculator.ui.theme.BMI_CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMI_CalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var result: Double by remember { mutableStateOf(0.0) }
    val classification by remember { mutableStateOf("")
    }



    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = height,
            onValueChange = { height = it },
            label = {
                Text(text = "Height (M)")
            }
        )

        TextField(
            value = weight,
            onValueChange = { weight = it },
            label = {
                Text(text = "Weight (KG)")
            },
            modifier = Modifier.padding(top = 8.dp)
        )

        Button(
            onClick = {
                result = calculateBmi(
                    height = height.toDouble(),
                    weight = weight.toDouble()
                )
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Calculate BMI")
        }

        if (result.toString().isNotBlank()) {
            Text(text = "Your BMI is: $result")
        }

        if (classification.isNotBlank()){
            Text(text = bmiClassification(result))
        }
    }

}
private fun calculateBmi(weight: Double, height: Double):  Double{
    val bmiIndex = weight / (height * height); return bmiIndex
}


private fun bmiClassification(bmi: Double): String{
    var type = ""
    if (bmi < 18.5) {
      type = "UnderWeight"
    } else if (bmi > 18.5 && bmi < 24.9) {
      type = "Normal"
    } else if (bmi > 25.0 && bmi < 29.9) {
      type = "Overweight"
    } else if (bmi > 30.0){
      type = "Obesity"
    }
    return type
}

@Preview
@Composable
fun MainScreenPrev(){
    BMI_CalculatorTheme {
        MainScreen()
    }

}
