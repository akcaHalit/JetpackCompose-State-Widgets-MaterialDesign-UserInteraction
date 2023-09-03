package com.halitakca.jetpackcomposetomvmm

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
fun SayfaA(navController: NavController) {
    val sayac = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Page A", fontSize = 50.sp)

        Button(onClick = {
            navController.navigate("page_b")
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