package com.becomedev.unliminetpro.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.becomedev.unliminetpro.data.navigation.AppNavHost
import com.becomedev.unliminetpro.ui.theme.UnliminetProTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp(){
    UnliminetProTheme {
        AppNavHost(modifier = Modifier.padding())
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApp()
}