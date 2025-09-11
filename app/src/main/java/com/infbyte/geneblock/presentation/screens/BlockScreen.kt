package com.infbyte.geneblock.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.infbyte.shared.models.Block
import com.infbyte.shared.models.Currency
import java.text.DateFormat
import java.util.Date

@Composable
fun BlockScreen(block: Block) {
    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Row(
            Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painterResource(block.iconResId!!),
                "",
                Modifier.padding(8.dp).size(32.dp),
            )
            Text(
                "${block.currency.name} (${block.currency.code})",
                Modifier.weight(1f),
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                DateFormat.getDateInstance(DateFormat.MEDIUM).format(Date(block.date)),
                Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        BlockInfo(stringResource(R.string.geneblock_hash), block.hash)
        BlockInfo(stringResource(R.string.geneblock_distance), block.distance.toString())
        BlockInfo(stringResource(R.string.geneblock_size), block.size.toString())
        BlockInfo(stringResource(R.string.geneblock_txs), block.transactions.toString())
        BlockInfo(stringResource(R.string.geneblock_miner), block.miner)
        BlockInfo(stringResource(R.string.geneblock_reward), "${block.reward} ${block.currency.code}")
    }
}

@Composable
fun BlockInfo(
    title: String,
    info: String,
) {
    Column(
        Modifier.padding(8.dp),
    ) {
        Text(
            title,
            Modifier.padding(8.dp),
            style = MaterialTheme.typography.titleSmall,
        )
        Text(
            info,
            Modifier.padding(start = 8.dp, end = 8.dp),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
fun PreviewBlockScreen() {
    BlockScreen(
        Block(
            hash = "000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f",
            currency = Currency("Bitcoin", "BTC"),
        ),
    )
}
