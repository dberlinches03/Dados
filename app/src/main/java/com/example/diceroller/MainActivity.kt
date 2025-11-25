package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }
    }
}

@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage()
}

@Preview(showBackground = true)
@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier.fillMaxSize()) {
    var result1 by remember { mutableStateOf(1) }
    var result2 by remember { mutableStateOf(1) }
    var showSecondDice by remember { mutableStateOf(false) }
    var totalPoints by remember { mutableStateOf(0) }


    val imageResource1 = when (result1) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(imageResource1),
            contentDescription = result1.toString()
        )

        if (showSecondDice) {
            val imageResource2 = when (result2) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(imageResource2),
                contentDescription = result2.toString()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        // Boton 1: lanza solo el pimer dado

        Button(onClick = {
            result1 = (1..6).random()
            showSecondDice = false
            totalPoints = result1
        }) {
            Text(stringResource(R.string.roll_one))
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Boton 2: lanza ambos dados

        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            result1 = (1..6).random()
            result2 = (1..6).random()
            showSecondDice = true
            totalPoints = result1 + result2
        }) {
            Text(stringResource(R.string.roll_both))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(R.string.total_points, totalPoints))
    }
}