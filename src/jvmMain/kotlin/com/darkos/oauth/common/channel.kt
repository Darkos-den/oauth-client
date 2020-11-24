package com.darkos.oauth.common

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

fun <T: Any>Channel<T>.sendAndClose(data: T){
    GlobalScope.launch {
        send(data)
        close()
    }
}