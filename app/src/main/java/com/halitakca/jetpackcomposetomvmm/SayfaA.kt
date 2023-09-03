package com.halitakca.jetpackcomposetomvmm

import android.util.Log
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SayfaA(navController: NavController,kisiObject: Kisiler) {
    val sayac = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Page A", fontSize = 50.sp)

        Text(text = kisiObject.isim)
        Text(text = kisiObject.yas.toString())
        Text(text = kisiObject.boy.toString())
        Text(text = kisiObject.bekarMi.toString())

        Button(onClick = {
            navController.navigate("page_b"){
                popUpTo("page_a"){
                    inclusive = true
                    Log.e("page_a pop","Page A is Popped")
                }
            }
        }) {
            Text(text = "Go to Page B")
        }
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Go Back")
        }
    }
}