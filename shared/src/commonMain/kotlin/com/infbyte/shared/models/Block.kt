package com.infbyte.shared.models

import kotlinx.serialization.Serializable

@Serializable
data class Block(
    val hash: String,
    val date: Long,
    val height: Int,
    val depth: Int,
    val distance: Long,
    val capacity: Float,
    val size: Int,
    val currency: Currency,
    val value: Float,
    val valueToday: Float,
    val input: Currency,
    val output: Currency,
    val transactions: Int,
    val inputs: Int,
    val outputs: Int,
    val miner: String,
    val reward: Float
)
