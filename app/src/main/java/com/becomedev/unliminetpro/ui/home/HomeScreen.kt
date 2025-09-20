package com.becomedev.unliminetpro.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.becomedev.unliminetpro.BuildConfig
import com.becomedev.unliminetpro.R
import com.becomedev.unliminetpro.data.model.NetworkPromotionModel
import com.becomedev.unliminetpro.data.model.PromotionModel
import com.becomedev.unliminetpro.network.UiState
import com.becomedev.unliminetpro.ui.AdMobBanner
import com.becomedev.unliminetpro.ui.MainViewModel
import com.becomedev.unliminetpro.ui.TwoButtonDialog
import com.becomedev.unliminetpro.ui.theme.ButtonDarkBlue
import com.becomedev.unliminetpro.ui.theme.ButtonDarkGreen
import com.becomedev.unliminetpro.ui.theme.ButtonDarkRed
import com.becomedev.unliminetpro.ui.theme.UnliminetProTheme
import com.becomedev.unliminetpro.ui.theme.fontDefault
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavHostController, viewModel: MainViewModel = koinViewModel()) {
    UnliminetProTheme {
        var selectedIndex by remember { mutableStateOf(0) }
        val uiState by viewModel.truemove.collectAsState()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomNavigationBar(selectedIndex) { index ->
                    selectedIndex = index

                    viewModel.getPackageTrueMove(index)
                }
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                AdMobBanner(BuildConfig.ADMOB_APP_ID)

                // Main content
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    when (uiState) {
                        is UiState.Loading -> {
                            CircularProgressIndicator()
                        }

                        is UiState.Error -> {
                            Text(
                                text = (uiState as UiState.Error).message,
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }

                        is UiState.Success -> {
                            val dataList = (uiState as UiState.Success<PromotionModel>).data
                            LazyColumn {
                                items(dataList.network) { item ->
                                    PackageCardItem(item)
                                }
                                item {
                                    Spacer(modifier = Modifier.height(14.dp))
                                }
                            }
                        }
                    }

                }
            }

        }
    }

}


data class NavItem(val label: String, val icon: Painter)

@Composable
fun BottomNavigationBar(selectedIndex: Int, onItemSelected: (Int) -> Unit) {
    val items = listOf(
        NavItem(stringResource(R.string.txt_true), painterResource(R.drawable.ic_true)),
        NavItem(stringResource(R.string.txt_ais), painterResource(R.drawable.ic_ais)),
        NavItem(stringResource(R.string.txt_dtac), painterResource(R.drawable.ic_dtac))
    )
    NavigationBar() {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onItemSelected(index) },
                icon = {
                    Image(
                        painter = item.icon,
                        contentDescription = "",
                        modifier = Modifier.size(36.dp)
                    )
                },
                label = { Text(item.label, fontFamily = fontDefault) }
            )
        }
    }
}

@Composable
fun PackageCardItem(packageItem: NetworkPromotionModel) {

    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var hasPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context,Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasPermission = granted
        if (granted) {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${packageItem.tel}"))
            context.startActivity(intent)
        }
    }

    ElevatedCard(
        onClick = {},
        colors = androidx.compose.material3.CardDefaults.elevatedCardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
            .padding(horizontal = 12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "${packageItem.price} บาท / ${packageItem.day} วัน",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(14.dp))

            //internet
            PackageDetailItem(
                icon = painterResource(R.drawable.ic_global) as VectorPainter,
                title = "${packageItem.qtyNet} ${packageItem.speedNet}"
            )
            Spacer(modifier = Modifier.height(8.dp))

            if (packageItem.freeCall.isNotEmpty()) {
                //free_call
                PackageDetailItem(
                    icon = painterResource(R.drawable.ic_phone_call) as VectorPainter,
                    title = packageItem.freeCall
                )
                Spacer(modifier = Modifier.height(8.dp))
            }


            if (packageItem.freeWifi.isNotEmpty()) {
                //free_wifi
                PackageDetailItem(
                    icon = painterResource(R.drawable.ic_wifi) as VectorPainter,
                    title = packageItem.freeWifi
                )
                Spacer(modifier = Modifier.height(8.dp))
            }


            //price total
            val totalPrice = ((packageItem.price * 7f / 100f) + packageItem.price)
            PackageDetailItem(
                icon = painterResource(R.drawable.ic_dollar) as VectorPainter,
                title = "ราคารวมภาษี $totalPrice บาท"
            )

            val btnColor = when (packageItem.name) {
                "true" -> ButtonDarkRed
                "ais" -> ButtonDarkGreen
                else -> ButtonDarkBlue
            }

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                ElevatedButton(
                    onClick = {
                        showDialog = true
                    }, colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = btnColor
                    )
                ) {
                    Icon(Icons.Default.Call, contentDescription = "สมัครเลย", tint = Color.White)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("สมัครเลย", fontFamily = fontDefault, color = Color.White)
                }
            }
        }
    }

    //confirm dialog
    if (showDialog) {
        TwoButtonDialog(
            title = "สมัครโปรโมชัน",
            message = "ต้องการสมัครโปร ${packageItem.qtyNet} ${packageItem.speedNet} ราคา ${packageItem.price} บาท ใช่หรือไม่?",
            confirmText = "สมัครเลย",
            dismissText = "ยกเลิก",
            onConfirm = {
                showDialog = false

                if (hasPermission) {
                    val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${packageItem.tel}"))
                    context.startActivity(intent)
                } else {
                    launcher.launch(Manifest.permission.CALL_PHONE)
                }

            },
            onDismiss = { /* handle dismiss */ showDialog = false },
            onDismissRequest = { showDialog = false }
        )
    }
}


@Composable
fun PackageDetailItem(icon: VectorPainter, title: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(18.dp),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
    }
}




@Preview
@Composable
fun PreviewItem() {
    UnliminetProTheme {
        HomeScreen(rememberNavController())

        //ProductItemShimmer()
    }
}