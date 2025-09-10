package com.infbyte.shared.models

import io.ktor.util.date.getTimeMillis
import kotlinx.serialization.Serializable

@Serializable
data class Block(
    val hash: String,
    val date: Long,
    val size: Int,
    val currency: Currency,
    val transactions: Int,
    val miner: String,
    val reward: Float,
) {
    private var distance: Long = 0L

    fun calculateDistance() {
        distance = getTimeMillis() - date
    }
}
