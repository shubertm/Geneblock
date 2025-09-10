package com.infbyte.geneblock.data.repo

import com.infbyte.shared.models.Block
import kotlinx.coroutines.flow.Flow

interface Repo {
    fun getAll(): Flow<List<Block>>
}