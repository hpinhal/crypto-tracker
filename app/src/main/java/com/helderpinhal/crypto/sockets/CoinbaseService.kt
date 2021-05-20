package com.helderpinhal.crypto.sockets

import com.helderpinhal.crypto.sockets.models.Subscribe
import com.helderpinhal.crypto.sockets.models.Ticker
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow

interface CoinbaseService {

    @Receive
    fun observeWebSocket(): Flow<WebSocket.Event>

    @Send
    fun sendSubscribe(subscribe: Subscribe)

    @Receive
    fun observeTicker(): Flow<Ticker>
}
