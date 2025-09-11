package com.infbyte.geneblock.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infbyte.geneblock.R
import com.infbyte.geneblock.data.repo.Repo
import com.infbyte.shared.models.Block
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class GenesisState(
    val currentBlock: Block = Block(),
    val allBlocks: List<Block> = listOf(),
) {
    companion object {
        val INITIAL_STATE = GenesisState()
    }
}

class GenesisViewModel(
    private val genesisRepo: Repo,
) : ViewModel() {
    var state by mutableStateOf(GenesisState.INITIAL_STATE)
        private set

    private val iconResIds =
        mapOf<String, Int>(
            "BTC" to R.drawable.ic_btc,
            "BCH" to R.drawable.ic_bch,
            "ETH" to R.drawable.ic_eth,
            "LTC" to R.drawable.ic_ltc,
            "DOGE" to R.drawable.ic_doge,
        )

    fun init() {
        viewModelScope.launch {
            genesisRepo.getAll().collectLatest { blocks ->
                val allBlocks =
                    blocks.map {
                        it.setIcon(iconResIds[it.currency.code])
                        it
                    }
                state = state.copy(allBlocks = allBlocks)
            }
        }
    }
}
