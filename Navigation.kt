package com.example.navcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(
            route=Screen.MainScreen.route
        ){
            MainScreen(navController=navController)
        }
        composable(
            route=Screen.DetailScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name"){
                    type= NavType.StringType
                    defaultValue="Anjani"
                    nullable=true
                }
            )
        ){
            entry->
            DetailScreen(name = entry.arguments?.getString("name"))
        }
    }
}



@Composable
fun MainScreen(navController: NavController) {
    var text by remember {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    ) {
        Text(text = "Welcome to JetPack Compose")
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                navController.navigate(Screen.DetailScreen.withArgs(text))
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Continue")
        }
    }
}


@Composable
fun DetailScreen(name:String?){
    Surface(color = MaterialTheme.colorScheme.primary,
       ){
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()){
            Text(text = "Hello, $name",
                modifier = Modifier.border(width = 2.dp,
                    color = Color.Red,shape = RoundedCornerShape(5.dp)).fillMaxWidth()
            )
        }
    }

}

