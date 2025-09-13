package com.infbyte.shared.logging

interface Logger {
    fun debug(
        tag: String,
        message: String,
    ) {}
}

expect object GLogger : Logger
