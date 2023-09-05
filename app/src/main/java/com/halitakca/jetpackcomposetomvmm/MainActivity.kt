package com.halitakca.jetpackcomposetomvmm

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
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

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun Anasayfa2(){
    val menuControl = remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
            Box{
                Button(onClick = {
                    menuControl.value = true

                }){ Text(text = "SHOW")}
                DropdownMenu(expanded = menuControl.value,
                    onDismissRequest = { menuControl.value = false }) {

                    DropdownMenuItem(onClick = {
                        Log.e("DropMenu","Delete")
                    }) { Text(text = "Delete")}
                    DropdownMenuItem(onClick = {
                        Log.e("DropMenu","Update")
                    }) { Text(text = "Update")}
                }
            }
    }


    /*
    val url = "https://www.geeksforgeeks.org/webview-in-android-using-jetpack-compose/"
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    }, update = {
        it.loadUrl(url)
    }
     */
    /*
    val progressStatus = remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
        if(progressStatus.value){
            CircularProgressIndicator(color = Color.Black)
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                progressStatus.value = true
            }) {
                Text(text = "Başla")
            }
            Button(onClick = {
                progressStatus.value = false
            }) {
                Text(text = "Dur")
            }
        }
    }
     */
    /*
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.size(100.dp).background(Color.Red)
            .pointerInput(Unit){ detectTapGestures(
                onTap = {
                    Log.e("Box","Tıklandı")
                },
                onDoubleTap = {
                    Log.e("Box","Çift Tıklandı")
                },
                onLongPress = {
                    Log.e("Box","Uzun Tıklandı")
                }
            )
            })
            /*.clickable {
                Log.e("Box","Box clickable")
            })
             */
    }
     */
    /*
    Scaffold(
        content = {
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                Log.e("HomePage2","FAB clicked")
            },
                backgroundColor = Color.Red,
                content = {
                    Icon(painter = painterResource(id = R.drawable.ekle_resim),
                        contentDescription ="Açıklama",
                        tint = Color.White )
                })})
     */
    /*
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
     */

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