package com.infbyte.shared.logging

import android.util.Log

actual object GLogger : Logger {
    override fun debug(
        tag: String,
        message: String,
    ) {
        Log.d(tag, message)
    }
}
