package com.infbyte.geneblock.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infbyte.geneblock.R
import com.infbyte.shared.models.Block
import com.infbyte.shared.models.Currency
import java.util.Date

@Composable
fun Block(block: Block, onClick:() -> Unit = {}) {
    Row(
        Modifier.fillMaxWidth().padding(8.dp).background(MaterialTheme.colorScheme.background)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(R.drawable.ic_btc),
            "",
            Modifier.padding(8.dp).size(48.dp)
        )
        Column(
            Modifier.fillMaxWidth().padding(8.dp).weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(block.currency.name, style = MaterialTheme.typography.titleMedium)
            Text(            Date(block.date).toString(), style = MaterialTheme.typography.labelSmall)
        }
        Text(
            block.currency.code,
            Modifier.wrapContentWidth().padding(8.dp),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Preview
@Composable
fun PreviewBlock() {
    Block(Block(currency = Currency("Bitcoin", "BTC")))
}