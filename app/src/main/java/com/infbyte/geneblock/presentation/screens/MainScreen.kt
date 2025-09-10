package com.infbyte.geneblock.presentation.screens

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.infbyte.geneblock.presentation.viewmodels.GenesisViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {
    val genesisViewModel: GenesisViewModel = koinViewModel()

    LaunchedEffect("") {
        genesisViewModel.init()
    }

    Scaffold(
        Modifier.navigationBarsPadding(),
    ) { innerPadding ->
        val navController = rememberNavController()
        NavHost(
            navController,
            Screens.BLOCKS,
            Modifier.padding(
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding()
            )
        ) {
            composable(Screens.BLOCKS) {
                BlocksScreen(genesisViewModel.state.allBlocks)
            }
        }
    }
}
