package com.becomedev.unliminetpro.ui.home

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.becomedev.unliminetpro.R
import com.becomedev.unliminetpro.data.model.NetworkPromotionModel
import com.becomedev.unliminetpro.data.model.PromotionModel
import com.becomedev.unliminetpro.network.UiState
import com.becomedev.unliminetpro.ui.MainViewModel
import com.becomedev.unliminetpro.ui.theme.ButtonDarkRed
import com.becomedev.unliminetpro.ui.theme.UnliminetProTheme
import com.becomedev.unliminetpro.ui.theme.fontDefault
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavHostController , viewModel: MainViewModel = koinViewModel(), ) {
    UnliminetProTheme {
        var selectedIndex by remember { mutableStateOf(0) }
        val uiState by viewModel.truemove.collectAsState()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomNavigationBar(selectedIndex) { index ->
                    selectedIndex = index
                }
            },
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {

                when(uiState){
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
fun PackageCardItem(packageItem : NetworkPromotionModel) {
    ElevatedCard(
        onClick = {},
        colors = androidx.compose.material3.CardDefaults.elevatedCardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
            .padding(horizontal = 12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "${packageItem.price} บาท / ${packageItem.day} วัน", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(modifier = Modifier.height(14.dp))

            //internet
            PackageDetailItem(
                icon = painterResource(R.drawable.ic_global) as VectorPainter,
                title = "${packageItem.qtyNet} ${packageItem.speedNet}"
            )
            Spacer(modifier = Modifier.height(8.dp))

            if(packageItem.freeCall.isNotEmpty()) {
                //free_call
                PackageDetailItem(
                    icon = painterResource(R.drawable.ic_phone_call) as VectorPainter,
                    title = packageItem.freeCall
                )
                Spacer(modifier = Modifier.height(8.dp))
            }


            if(packageItem.freeWifi.isNotEmpty()) {
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

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                ElevatedButton(onClick = {}, colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = ButtonDarkRed
                )) {
                    Icon(Icons.Default.Call, contentDescription = "สมัครเลย", tint = Color.White)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("สมัครเลย", fontFamily = fontDefault, color = Color.White)
                }
            }
        }
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
        //HomeScreen(rememberNavController())
//        LoadingOverlay()

        //ProductItemShimmer()
    }
}