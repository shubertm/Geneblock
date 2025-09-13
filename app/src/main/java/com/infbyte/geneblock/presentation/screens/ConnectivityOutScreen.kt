package com.infbyte.geneblock.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infbyte.geneblock.R

@Preview
@Composable
fun ConnectivityOutScreen(onRefresh: () -> Unit = {}) {
    Box(
        Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                painterResource(R.drawable.ic_cloud_off),
                "",
                Modifier.size(32.dp),
                MaterialTheme.colorScheme.primary,
            )
            Text(
                stringResource(R.string.geneblock_connectivity_out),
                Modifier.padding(8.dp),
                style = MaterialTheme.typography.titleMedium,
            )
            Button(onRefresh) {
                Text(stringResource(R.string.geneblock_try_again))
            }
        }
    }
}
