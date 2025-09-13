package com.infbyte.geneblock.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infbyte.geneblock.R
import com.infbyte.geneblock.data.repo.Repo
import com.infbyte.shared.models.Block
import com.infbyte.shared.network.NetworkResult
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

data class GenesisSideEffect(
    val requestTimeout: Boolean = false,
    val connectivityOut: Boolean = false,
    val message: String = "",
) {
    companion object {
        val INITIAL_EFFECT = GenesisSideEffect()
    }
}

class GenesisViewModel(
    private val genesisRepo: Repo,
) : ViewModel() {
    var state by mutableStateOf(GenesisState.INITIAL_STATE)
        private set
    var sideEffect by mutableStateOf(GenesisSideEffect.INITIAL_EFFECT)
        private set

    private val iconResIds =
        mapOf<String, Int>(
            "BTC" to R.drawable.ic_btc,
            "BCH" to R.drawable.ic_bch,
            "ETH" to R.drawable.ic_eth,
            "LTC" to R.drawable.ic_ltc,
            "DOGE" to R.drawable.ic_doge,
        )

    fun fetchAllBlocks() {
        viewModelScope.launch {
            genesisRepo
                .getAll(
                    onNetworkResult = { networkResult ->
                        when (networkResult) {
                            NetworkResult.Timeout -> {
                                sideEffect = sideEffect.copy(requestTimeout = true)
                            }
                            NetworkResult.ConnectivityOut -> {
                                sideEffect = sideEffect.copy(connectivityOut = true)
                            }
                            NetworkResult.Success -> {}
                        }
                    },
                ).collectLatest { blocks ->
                    val allBlocks =
                        blocks.map {
                            it.setIcon(iconResIds[it.currency.code])
                            it
                        }
                    state = state.copy(allBlocks = allBlocks)
                }
        }
    }

    fun onRefresh() {
        viewModelScope.launch {
            sideEffect = sideEffect.copy(requestTimeout = false, connectivityOut = false)
        }
    }

    fun onClickBlock(block: Block) {
        viewModelScope.launch {
            state = state.copy(currentBlock = block)
        }
    }
}
