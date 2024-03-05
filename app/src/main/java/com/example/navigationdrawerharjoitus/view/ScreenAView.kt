package com.example.navigationdrawerharjoitus.view

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenA(onMenuClick: () -> Unit) {
    Scaffold( topBar = {
        TopAppBar(
            title = {Text("Screen A")},
            navigationIcon = {
                IconButton(onClick = {
                    onMenuClick()
                }) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                }
            }
        )
    }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            //val screenAVm: ScreenAViewModel = viewModel()
            Text(
                text = "This is Screen A.",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )
        }
    }

}