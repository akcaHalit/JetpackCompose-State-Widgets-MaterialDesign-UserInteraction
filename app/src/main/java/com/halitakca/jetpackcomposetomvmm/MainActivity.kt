package com.halitakca.jetpackcomposetomvmm

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.google.gson.Gson
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
                   // SayfaGecisleri()
                    Anasayfa2()
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

        composable("page_a/{kisiObject}",
            arguments = listOf(
            navArgument("kisiObject"){type = NavType.StringType},
            )
        ){
            val json = it.arguments?.getString("kisiObject")
            val obj = Gson().fromJson(json,Kisiler::class.java)
            SayfaA(navController = navController, kisiObject = obj)
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
            val kisi = Kisiler("Halit",22,1.90f,false)
            val kisiJson = Gson().toJson(kisi)
            navController.navigate("page_a/$kisiJson")
        }) {
            Text(text = "Go to Page A")
        }
    }
}

@Composable
fun Anasayfa2(){
    var tf = remember { mutableStateOf("") }
    var veri = remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(value = tf.value, onValueChange = {tf.value = it})

        OutlinedTextField(value = tf.value, onValueChange = {tf.value = it})
        OutlinedButton(onClick = {
            veri.value = tf.value
        }) {
            Text(text = "Veriyi Outlined Al")
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
/*       Nabigations:
        composable("page_a"){
            SayfaA(navController = navController,)
        }
*/
/*
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
 */

/*              Life Cycle
    LaunchedEffect(key1 = true){
        Log.e("HomePage","Launch Effect")
    }
    SideEffect {
        Log.e("HomePage","Side Effect")
    }
    DisposableEffect(Unit){
        onDispose {
            Log.e("HomePage","Disposable Effect")
        }
    }
 */