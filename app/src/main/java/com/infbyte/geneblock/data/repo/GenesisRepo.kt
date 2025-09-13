package com.infbyte.geneblock.data.repo

import com.infbyte.shared.models.Block
import com.infbyte.shared.network.Client
import com.infbyte.shared.network.NetworkResult
import kotlinx.coroutines.flow.Flow

class GenesisRepo(
    private val client: Client<Block>,
) : Repo {
    override fun getAll(onNetworkResult: (NetworkResult) -> Unit): Flow<List<Block>> = client.getAll(onNetworkResult)
}
