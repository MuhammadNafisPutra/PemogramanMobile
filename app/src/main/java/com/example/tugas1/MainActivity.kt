package com.example.tugas1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppSapa()
        }
    }
}

@Composable
fun AppSapa() {
    var isDarkTheme by remember { mutableStateOf(false) }

    val warnaTema = if (isDarkTheme) {
        darkColorScheme()
    } else {
        lightColorScheme()
    }

    MaterialTheme(colorScheme = warnaTema) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AppKonten(
                isDark = isDarkTheme,
                onThemeChanged = { isDarkTheme = it }
            )
        }
    }
}

@Composable
fun AppKonten(isDark: Boolean, onThemeChanged: (Boolean) -> Unit) {
    var namaInput by remember { mutableStateOf("") }
    var teksSapaan by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Mode Gelap")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = isDark,
                onCheckedChange = { isChecked ->
                    onThemeChanged(isChecked)
                }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = namaInput,
            onValueChange = { namaInput = it },
            label = { Text("Masukkan Nama") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                teksSapaan = if (namaInput.isNotEmpty()) {
                    "Halo $namaInput"
                } else {
                    "Halo, nama belum diisi!"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("SAPA")
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (teksSapaan.isNotEmpty()) {
            Text(
                text = teksSapaan,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAplikasi() {
    AppSapa()
}