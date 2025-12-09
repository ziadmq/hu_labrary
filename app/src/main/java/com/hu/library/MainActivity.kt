package com.hu.library

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.hu.library.navigation.AppNavGraph
import com.hu.library.ui.components.BottomNavigationBar
import com.hu.library.ui.theme.HULibraryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HULibraryApp()
        }
    }
}

@Composable
fun HULibraryApp() {

    HULibraryTheme {

        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        ) { innerPadding ->

            AppNavGraph(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}