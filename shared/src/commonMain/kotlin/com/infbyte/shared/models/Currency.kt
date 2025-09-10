package com.infbyte.shared.models

import kotlinx.serialization.Serializable

@Serializable
data class Currency(
    val name: String = "",
    val code: String = "",
)
