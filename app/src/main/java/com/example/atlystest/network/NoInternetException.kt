package com.example.atlystest.network

import okio.IOException

class NoInternetException: IOException() {
    override val message: String
        get() = "No Internet Connection"

    override fun getLocalizedMessage(): String? {
        return "No Internet Connection"
    }
}