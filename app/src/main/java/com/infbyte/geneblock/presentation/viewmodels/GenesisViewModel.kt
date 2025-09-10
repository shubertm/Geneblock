package com.infbyte.geneblock.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infbyte.geneblock.data.repo.Repo
import com.infbyte.shared.models.Block
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class GenesisState(
    val currentBlock: Block = Block(),
    val allBlocks: List<Block> = listOf()
) {
    companion object {
        val INITIAL_STATE = GenesisState()
    }
}

class GenesisViewModel(
    private val genesisRepo: Repo
): ViewModel() {

    var state by mutableStateOf(GenesisState.INITIAL_STATE)
        private set

    fun init() {
        viewModelScope.launch {
            genesisRepo.getAll().collectLatest { blocks ->
                state = state.copy(allBlocks = blocks)
            }
        }
    }
}