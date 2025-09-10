package com.infbyte.geneblock.presentation.screens

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.infbyte.shared.models.Block
import com.infbyte.geneblock.presentation.views.Block
import com.infbyte.shared.models.Currency

@Composable
fun BlocksScreen(
    blocks: List<Block> = listOf(
        Block(currency = Currency("Bitcoin", "BTC")),
        Block(currency = Currency("Bitcoin", "BTC")),
        Block(currency = Currency("Bitcoin", "BTC")),
        Block(currency = Currency("Bitcoin", "BTC")),
        Block(currency = Currency("Bitcoin", "BTC")),
        Block(currency = Currency("Bitcoin", "BTC")),
        Block(currency = Currency("Bitcoin", "BTC")),
        Block(currency = Currency("Bitcoin", "BTC")),
        Block(currency = Currency("Bitcoin", "BTC")),
        Block(currency = Currency("Bitcoin", "BTC")),
    )
) {
    LazyColumn(Modifier.systemBarsPadding()) {
        items(blocks) {
            Block(it)
        }
    }
}

@Preview
@Composable
fun PreviewBlocksScreen() {
    BlocksScreen()
}