package com.becomedev.unliminetpro.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.becomedev.unliminetpro.data.navigation.AppNavHost
import com.becomedev.unliminetpro.ui.theme.UnliminetProTheme
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

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

@Composable
fun AdMobBanner(
    adUnitId: String = "ca-app-pub-3940256099942544/6300978111" // test banner id
) {
    AndroidView(
        factory = {
            AdView(it).apply {
                setAdSize(AdSize.BANNER)
                this.adUnitId = adUnitId
                loadAd(AdRequest.Builder().build())
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp) // ปรับตาม AdSize
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