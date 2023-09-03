package com.halitakca.jetpackcomposetomvmm

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SayfaB(navController: NavController) {
    val sayac = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Page B", fontSize = 50.sp)

        Button(onClick = {
            navController.navigate("homepage")
        }) {
            Text(text = "Go to Home Page")
        }
    }

    val activity = (LocalContext.current as Activity)
    BackHandler(onBack = {
        Log.e("PageB","Back button clicked.")
        activity.finish()
    })
}
