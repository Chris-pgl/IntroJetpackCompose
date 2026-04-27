package com.example.introtocompose

import android.R
import android.R.attr.elevation
import android.R.color.white
import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.introtocompose.ui.theme.IntroToComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntroToComposeTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(){
    val moneyCounter = remember { mutableStateOf(0) }
    Scaffold(modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF546E7A)
        //topBar = { AppBar }
        //bottomBar = { BottomBar }
        //floatingActionButton = { FAB }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "€ ${moneyCounter.value}",
                style = TextStyle(
                color = Color.White,
                fontSize = 35.sp, // sp è per la dimensione dei caratteri
                fontWeight = FontWeight.ExtraBold
            ))
            Spacer(modifier = Modifier.height(130.dp))
            CreateCircle(moneyCounter = moneyCounter.value){ newValue ->
                moneyCounter.value = newValue
            }
            if (moneyCounter.value > 25){
                Spacer(modifier = Modifier.height(40.dp))
                Text("Lots of money!", style = TextStyle(color = Color.White), fontSize = 34.sp)
            }
        }
    }
}

//@Preview
@Composable
fun CreateCircle(moneyCounter:Int = 0,
                 updateMonetCounter:(Int) -> Unit){
    //per far cambiare i dati alla UI bisogna delegare il dato al mutableState
    // ma ogni volta viene ricreato..
    //usando remember{} i dati vengono 'salvati' nella recomposition ( muore a ogni rotazione schermo etc)
    // per sopravvivere alla rotazione serve rememberSaveable { }

    Card(modifier = Modifier
        .padding(3.dp)
        .size(145.dp)
        .clickable {
           updateMonetCounter(moneyCounter + 1)
            Log.d("Money", "MoneyCounter: $moneyCounter ")
        },
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(text = "TAP", modifier = Modifier)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntroToComposeTheme {
        MyApp()
    }
}