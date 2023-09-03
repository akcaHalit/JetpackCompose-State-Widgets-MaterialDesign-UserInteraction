package com.halitakca.jetpackcomposetomvmm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.halitakca.jetpackcomposetomvmm.ui.theme.JetpackComposeToMVMMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  setContent'te =>    İçerikler: Temalar, Sayfalar
        setContent {
            //  Tema'mız
            JetpackComposeToMVMMTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    SayfaGecisleri()
                }
            }
        }
    }
}

@Composable
fun SayfaGecisleri(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "homepage"){
        composable("homepage"){
            AnaSayfa(navController = navController)
        }
/*      composable("page_a"){
            SayfaA(navController = navController,)
        } */
        composable("page_a/{name}/{yas}/{boy}",
        arguments = listOf(
            navArgument("name"){type = NavType.StringType},
            navArgument("yas"){type = NavType.IntType},
            navArgument("boy"){type = NavType.FloatType}
        )
        ){
            val name = it.arguments?.getString("name")!!
            val yas = it.arguments?.getInt("yas")!!
            val boy = it.arguments?.getFloat("boy")!!
            SayfaA(navController = navController, name = name, yas = yas, boy = boy)
        }
        composable("page_b"){
            SayfaB(navController = navController)
        }
    }


}

@Composable
fun AnaSayfa(navController: NavController){
    val sayac = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Home Page", fontSize =50.sp)

        Text(text = "Sayaç: ${sayac.value}")

        Button(onClick = { sayac.value += 5}) {
            Text(text = "Sayacı arttır.")
        }

        Button(onClick = {
            navController.navigate("page_a/Halit/22/190")
        }) {
            Text(text = "Go to Page A")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeToMVMMTheme {
        SayfaGecisleri()
    }
}