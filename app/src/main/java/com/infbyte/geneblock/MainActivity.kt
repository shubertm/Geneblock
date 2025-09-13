package com.infbyte.geneblock

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.infbyte.geneblock.presentation.screens.MainScreen
import com.infbyte.geneblock.presentation.screens.NoNetworkScreen
import com.infbyte.geneblock.ui.theme.GeneblockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        var hasInternetConnection by mutableStateOf(false)

        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        val networkRequest =
            NetworkRequest
                .Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build()
        val networkCallback =
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    hasInternetConnection = true
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    hasInternetConnection = false
                }
            }

        connectivityManager.requestNetwork(networkRequest, networkCallback)

        enableEdgeToEdge()
        setContent {
            GeneblockTheme {
                if (!hasInternetConnection) {
                    NoNetworkScreen()
                    return@GeneblockTheme
                }
                MainScreen()
            }
        }
    }
}
