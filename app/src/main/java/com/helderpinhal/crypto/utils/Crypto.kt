package com.helderpinhal.crypto.utils

enum class Crypto {
    BITCOIN,
    ETHEREUM,
    CARDANO,
    CHAINLINK,
    LITECOIN;

    val id: String
        get() {
            return when (this) {
                BITCOIN -> "BTC-USD"
                ETHEREUM -> "ETH-USD"
                CARDANO -> "ADA-USD"
                CHAINLINK -> "LINK-USD"
                LITECOIN -> "LTC-USD"
            }
        }

    val code: String
        get() {
            return when (this) {
                BITCOIN -> "BTC"
                ETHEREUM -> "ETH"
                CARDANO -> "ADA"
                CHAINLINK -> "LINK"
                LITECOIN -> "LTC"
            }
        }

    val friendlyName: String
        get() {
            return when (this) {
                BITCOIN -> "Bitcoin"
                ETHEREUM -> "Ethereum"
                CARDANO -> "Cardano"
                CHAINLINK -> "Chainlink"
                LITECOIN -> "Litecoin"
            }
        }
}
