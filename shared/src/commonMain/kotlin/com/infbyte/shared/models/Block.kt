package com.infbyte.shared.models

import io.ktor.util.date.getTimeMillis
import kotlinx.serialization.Serializable

@Serializable
data class Block(
    val hash: String = "",
    val date: Long = 0L,
    val size: Int = 0,
    val currency: Currency = Currency(),
    val transactions: Int = 0,
    val miner: String = "",
    val reward: Float = 0F
) {
    private var distance: Long = 0L
    var iconResId: Int? = null
        private set

    fun setIcon(iconResId: Int) {
        this.iconResId = iconResId
    }

    fun calculateDistance() {
        distance = getTimeMillis() - date
    }
}
