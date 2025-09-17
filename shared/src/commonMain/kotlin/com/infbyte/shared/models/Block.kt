package com.infbyte.shared.models

import io.ktor.util.date.getTimeMillis
import kotlinx.serialization.Serializable

@Serializable
data class Block(
    val hash: String = "",
    val date: Long = 0L,
    val size: Float = 0F,
    val currency: Currency = Currency(),
    val transactions: Int = 0,
    val miner: String = "",
    val reward: Float = 0F,
) {
    var distance: Long = 0L
        private set

    var iconResId: Int? = null
        private set

    fun setIcon(iconResId: Int?) {
        this.iconResId = iconResId
    }

    fun calculateDistance() {
        distance = getTimeMillis() - date
    }
}
