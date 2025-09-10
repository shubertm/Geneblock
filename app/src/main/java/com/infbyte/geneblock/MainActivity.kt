package com.infbyte.geneblock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.infbyte.geneblock.presentation.screens.MainScreen
import com.infbyte.geneblock.ui.theme.GeneblockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeneblockTheme {
                MainScreen()
            }
        }
    }
}
