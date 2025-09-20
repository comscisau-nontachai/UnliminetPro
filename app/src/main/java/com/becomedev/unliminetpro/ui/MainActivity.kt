package com.becomedev.unliminetpro.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun MyApp() {
    UnliminetProTheme {
        AppNavHost(modifier = Modifier.padding())
    }
}


@Composable
fun TwoButtonDialog(
    title: String,
    message: String,
    confirmText: String,
    dismissText: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    onDismissRequest: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(title) },
        text = { Text(message) },
        confirmButton = {
            androidx.compose.material3.TextButton(
                onClick = onConfirm,
                modifier = Modifier.background(Color(0xFF4CAF50), shape = RoundedCornerShape(12.dp))
            ) {
                Text(text = confirmText , color = Color.White , fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            androidx.compose.material3.TextButton(onClick = onDismiss) {
                Text(dismissText)
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    //MyApp()

    TwoButtonDialog(
        title = "สมัครโปรโมชัน",
        message = "ต้องการสมัครโปร",
        confirmText = "สมัครเลย",
        dismissText = "ยกเลิก",
        onConfirm = { /* handle confirm */ },
        onDismiss = { /* handle dismiss */ },
        onDismissRequest = { }
    )
}