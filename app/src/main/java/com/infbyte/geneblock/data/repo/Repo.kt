package com.infbyte.geneblock.data.repo

import com.infbyte.shared.models.Block
import com.infbyte.shared.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface Repo {
    fun getAll(onNetworkResult: (NetworkResult) -> Unit): Flow<List<Block>>
}
